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

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager("fr-BE");

        manager.parseProduct("D,101,Tea,1.99,0,2020-09-19");
        manager.printProductReport(101);
        manager.parseReview("101,2,Rather weak tea");
        manager.parseReview("101,4,Good tea.");
        manager.parseReview("101,3,Nice hot cup of tea!");
        manager.parseReview("101,1,Disappointing.");
        manager.parseReview("101,3,Just add some lemon.");
        manager.parseReview("101,5,Perfect tea.");
        manager.printProductReport(101);

        manager.parseProduct("F,102,Cake,3.99,0,2020-09-19");
        manager.printProductReport(102);
        manager.parseReview("102,2,Rather tasteless tea");
        manager.parseReview("102,4,Good cake.");
        manager.parseReview("102,3,Nice piece of cake!");
        manager.parseReview("102,1,Disappointing.");
        manager.parseReview("102,3,Just add some sugar.");
        manager.parseReview("102,5,Perfect cake.");
        manager.printProductReport(102);

    }
}