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

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {

    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Product tea = manager.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        Product coffee = manager.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.FOUR_RATED);
        Product cake = manager.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_RATED, LocalDate.now());
        Product chocolate = manager.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_RATED, LocalDate.now());
        Product chocolateDrink = manager.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_RATED);

        chocolate = chocolate.applyRating(Rating.FOUR_RATED);

        System.out.println(tea);
        System.out.println(coffee);
        System.out.println(cake);
        System.out.println(chocolate);
        System.out.println(chocolateDrink);
    }
}
