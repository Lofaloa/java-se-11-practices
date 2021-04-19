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

import me.loganfarci.tutorials.shop.manager.data.Product;
import me.loganfarci.tutorials.shop.manager.data.Review;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

public class ResourceFormatter {

    private static Map<String, ResourceFormatter> formatters = Map.of(
            "en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US),
            "fr-FR", new ResourceFormatter(Locale.FRANCE),
            "fr-BE", new ResourceFormatter(new Locale("fr", "BE")),
            "ru-RU", new ResourceFormatter(new Locale("ru", "RU")),
            "zh-CN", new ResourceFormatter(Locale.CHINA),
            "it-IT", new ResourceFormatter(new Locale("it", "IT"))
    );

    public static ResourceFormatter getResourceFormatter(String languageTag) {
        return formatters.getOrDefault(languageTag, formatters.get("en-GB"));
    }

    private static final String BUNDLE_BASE_NAME = "me.loganfarci.tutorials.shop.client.resources";
    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    private ResourceFormatter(Locale locale) {
        this.locale = locale;
        this.resources = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
        this.dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        this.moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public DateTimeFormatter getDateFormat() {
        return dateFormat;
    }

    public String getText(String key) {
        return resources.getString(key);
    }

    public String formatProduct(Product product) {
        return MessageFormat.format(getText("product"),
                product.getName(),
                moneyFormat.format(product.getPrice()),
                product.getRating().getStars(),
                dateFormat.format(product.getBestBefore()));
    }

    public String formatReview(Review review) {
        return MessageFormat.format(getText("review"),
                review.getRating().getStars(),
                review.getComments());
    }

    public String formatProductReport(Product product, List<Review> reviews) {
        Collections.sort(reviews);
        StringBuilder out = new StringBuilder();
        out.append(formatProduct(product)).append(System.lineSeparator());
        if (reviews.isEmpty()) {
            out.append(getText("no.reviews"));
        } else {
            out.append(reviews.stream()
                    .map(review -> formatReview(review) + System.lineSeparator())
                    .collect(Collectors.joining()));
        }
        return out.toString();
    }

    public String formatData(String key, int productId) {
        return MessageFormat.format(getText(key), productId);
    }
}
