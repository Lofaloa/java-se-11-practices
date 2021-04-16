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
        ProductManager manager = new ProductManager("fr-BE");
        manager.printProductReport(101);
        manager.printProductReport(102);
        manager.printProductReport(103);
        manager.printProductReport(104);
        manager.printProducts(p -> true, (a, b) -> b.getRating().ordinal() - a.getRating().ordinal());

        manager.createProduct(237, "Tiramisù", BigDecimal.valueOf(7.99), Rating.FIVE_RATED, LocalDate.now().plus(Period.ofDays(3)));
        manager.reviewProduct(237, Rating.THREE_RATED, "It was ok I guess.");
        manager.reviewProduct(237, Rating.TWO_RATED, "I hated it.");
        manager.reviewProduct(237, Rating.FIVE_RATED, "Mamma mia! It was so good.");
        manager.printProductReport(237);
    }
}