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
package me.loganfarci.tutorials.shop.client;

import me.loganfarci.tutorials.shop.file.service.ProductFileManager;
import me.loganfarci.tutorials.shop.manager.data.Product;
import me.loganfarci.tutorials.shop.manager.data.Rating;
import me.loganfarci.tutorials.shop.manager.data.Review;
import me.loganfarci.tutorials.shop.manager.service.ProductManager;
import me.loganfarci.tutorials.shop.manager.service.ProductManagerException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {

    private static final Logger logger = Logger.getLogger(Shop.class.getName());

    public static void main(String[] args) {
        try {
            ResourceFormatter formatter = ResourceFormatter.getResourceFormatter("fr-BE");
            ProductManager manager = new ProductFileManager();
            manager.createProduct(164, "Kombucha", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
            manager.reviewProduct(164, Rating.TWO_RATED, "Looks like tea but is it?");
            manager.reviewProduct(164, Rating.FOUR_RATED, "Fine tea");
            manager.reviewProduct(164, Rating.FOUR_RATED, "This is not tea");
            manager.reviewProduct(164, Rating.FIVE_RATED, "Perfect!");
            manager.findProducts(p -> p.getPrice().doubleValue() < 2).stream().forEach(p -> System.out.println(formatter.formatProduct(p)));
            Product product = manager.findProduct(101);
            List<Review> reviews = manager.findReviews(101);
            reviews.forEach(r -> System.out.println(formatter.formatReview(r)));
            printFile(formatter.formatProductReport(product, reviews), Path.of(formatter.formatData("report", product.getId())));
        } catch (ProductManagerException e) {
           logger.log(Level.WARNING, e.getMessage(), e);
        }
    }


    private static void printFile(String content, Path file) {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(Files.newOutputStream(file, StandardOpenOption.CREATE), StandardCharsets.UTF_8))) {
            out.append(content);
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
}