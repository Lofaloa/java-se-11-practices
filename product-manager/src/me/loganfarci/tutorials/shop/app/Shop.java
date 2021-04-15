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
package me.loganfarci.tutorials.shop.app;

import me.loganfarci.tutorials.shop.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Locale;

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager("fr-BE");
        
        manager.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        manager.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        manager.createProduct(103, "Cake", BigDecimal.valueOf(2.99), Rating.NOT_RATED, LocalDate.now().plus(Period.ofDays(2)));
        manager.createProduct(104, "Chocolate", BigDecimal.valueOf(3.99), Rating.NOT_RATED, LocalDate.now().plus(Period.ofDays(3)));

        manager.reviewProduct(101, Rating.FOUR_RATED, "Nice hot cup of tea!");
        manager.reviewProduct(101, Rating.FIVE_RATED, "Love tea!");
        manager.reviewProduct(101, Rating.ONE_RATED, "Hate it.");
        manager.reviewProduct(101, Rating.TWO_RATED, "Green tea, burk!");
        manager.reviewProduct(101, Rating.THREE_RATED, "That is all right I guess.");

        manager.reviewProduct(102, Rating.FOUR_RATED, "Nice hot cup of coffee!");
        manager.reviewProduct(102, Rating.FIVE_RATED, "Love coffee!");
        manager.reviewProduct(102, Rating.THREE_RATED, "That is all right I guess.");

        manager.reviewProduct(103, Rating.FOUR_RATED, "Nice hot cup of cake!");
        manager.reviewProduct(103, Rating.FIVE_RATED, "Love cake!");
        manager.reviewProduct(103, Rating.THREE_RATED, "That is all right I guess.");

        manager.reviewProduct(104, Rating.ONE_RATED, "Hate it.");
        manager.reviewProduct(104, Rating.TWO_RATED, "Green chocolate, burk!");

        manager.printProductReport(104);

        Comparator<Product> ratingSorter = (a, b) -> b.getRating().ordinal() - a.getRating().ordinal();
        Comparator<Product> priceSorter = (a, b) -> b.getPrice().compareTo(a.getPrice());

        manager.printProducts(p -> p.getPrice().floatValue() < 2, ratingSorter);
//        manager.printProducts(ratingSorter.thenComparing(priceSorter).reversed());
    }
}