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

import me.loganfarci.tutorials.shop.data.Product;
import me.loganfarci.tutorials.shop.data.Rating;

import java.math.BigDecimal;

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {

    private static void printProduct(final Product product) {
        String line = String.format(
                "%d %s %f %s",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getRating().getStars()
        );
        System.out.println(line);
    }

    public static void main(String[] args) {
        Product tea = new Product(101, "Tea", BigDecimal.valueOf(1.99));
        Product coffee = new Product(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_RATED);
        Product cake = new Product(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_RATED);
        Product p = new Product();

        cake = cake.applyRating(Rating.ONE_RATED);

        printProduct(tea);
        printProduct(coffee);
        printProduct(cake);
        printProduct(p);
    }
}
