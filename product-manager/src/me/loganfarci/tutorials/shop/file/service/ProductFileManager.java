/*
 * Copyright (C) 2021 Logan Farci

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Java Trove Examples. If not, see <http://www.gnu.org/licenses/>.
 */
package me.loganfarci.tutorials.shop.file.service;

import me.loganfarci.tutorials.shop.client.ResourceFormatter;
import me.loganfarci.tutorials.shop.manager.data.*;
import me.loganfarci.tutorials.shop.manager.service.ProductManager;
import me.loganfarci.tutorials.shop.manager.service.ProductManagerException;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductFileManager implements ProductManager {

    private static final Logger logger = Logger.getLogger(ProductFileManager.class.getName());
    private static final String CONFIG_BASE_NAME = "me.loganfarci.tutorials.shop.file.service.config";

    private Map<Product, List<Review>> products = new HashMap<>();
    private final ResourceBundle config = ResourceBundle.getBundle(CONFIG_BASE_NAME);
    private final MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));
    private final Path dataFolder = Path.of(config.getString("data.folder"));
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();

    public ProductFileManager() {
        loadAllData();
    }

    @Override
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    @Override
    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    @Override
    public Product findProduct(int id) throws ProductManagerException {
        try {
            readLock.lock();
            return products.keySet().stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new ProductManagerException("Unknown product id: " + id));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Product reviewProduct(int id, Rating rating, String comments) throws ProductManagerException {
            return reviewProduct(findProduct(id), rating, comments);
    }

    @Override
    public List<Product> findProducts(Predicate<Product> filter) throws ProductManagerException {
        return products.keySet().stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Review> findReviews(int id) throws ProductManagerException {
        return products.get(findProduct(id));
    }

    @Override
    public Map<String, BigDecimal> getDiscounts() {
            return products.keySet()
                    .stream()
                    .collect(
                            Collectors.groupingBy(
                                    product -> product.getRating().getStars(),
                                    Collectors.collectingAndThen(
                                            Collectors.summingDouble(p -> p.getDiscount().doubleValue()),
                                            discount -> BigDecimal.valueOf(discount)
                                    )));
    }

    private Product reviewProduct(Product product, Rating rating, String comments) {
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));
        product = product.applyRating(
                Rateable.convert((int) Math.round(
                        reviews.stream()
                                .mapToInt(r -> r.getRating().ordinal())
                                .average()
                                .orElse(0)
                )));
        products.put(product, reviews);
        return product;
    }

    private Review parseReview(String text) {
        Review review = null;
        try {
            Object[] values = reviewFormat.parse(text);
            review = new Review(Rateable.convert(Integer.parseInt((String) values[0])), (String) values[1]);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "Error while parsing review: " + text);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Could not parse review rating: " + text);
        }
        return review;
    }

    private Product parseProduct(String text) {
        Product product = null;
        try {
            Object[] values = productFormat.parse(text);
            int id = Integer.parseInt((String) values[1]);
            String name = (String) values[2];
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble((String) values[3]));
            Rating rating = Rateable.convert(Integer.parseInt((String) values[4]));
            switch ((String) values[0]) {
                case "D":
                    product = new Drink(id, name, price, rating);
                    break;
                case "F":
                    LocalDate bestBefore = LocalDate.parse((String) values[5]);
                    product = new Food(id, name, price, rating, bestBefore);
            }
        } catch (ParseException | NumberFormatException | DateTimeParseException e) {
            logger.log(Level.WARNING, "Error while parsing product: " + text + "" + e.getMessage());
        }
        return product;
    }

    private List<Review> loadReviews(Product product) {
        List<Review> reviews = null;
        Path file = dataFolder.resolve(MessageFormat.format(config.getString("reviews.data.file"), product.getId()));
        if (Files.notExists(file)) {
            reviews = new ArrayList<>();
        } else {
            try {
                reviews = Files.lines(file, Charset.forName("UTF-8"))
                        .map(text -> parseReview(text))
                        .filter(review -> review != null)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error while loading reviews " + e.getMessage());
            }
        }
        return reviews;
    }

    private Product loadProduct(Path file) {
        Product product = null;
        try {
            product = parseProduct(Files.lines(dataFolder.resolve(file), Charset.forName("UTF-8")).findFirst().orElseThrow());
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error while loading product " + e.getMessage());
        }
        return product;
    }

    private void loadAllData() {
        try {
            products = Files.list(dataFolder)
                    .filter(file -> file.getFileName().toString().startsWith("product"))
                    .map(file -> loadProduct(file))
                    .filter(product -> product != null)
                    .collect(Collectors.toMap(product -> product, product -> loadReviews(product)));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data " + e.getMessage());
        }
    }

}
