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
import java.util.Locale;

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {

    public static void main(String[] args) {
        ProductManager manager = new ProductManager(Locale.US);
        
        Product tea = manager.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        tea = manager.reviewProduct(tea, Rating.FOUR_RATED, "Nice hot cup of tea!");
        tea = manager.reviewProduct(tea, Rating.FIVE_RATED, "Love tea!");
        tea = manager.reviewProduct(tea, Rating.ONE_RATED, "Hate it.");
        tea = manager.reviewProduct(tea, Rating.TWO_RATED, "Green tea, burk!");
        tea = manager.reviewProduct(tea, Rating.THREE_RATED, "That is all right I guess.");
        manager.printProductReport(tea);

        Product coffee = manager.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        coffee = manager.reviewProduct(coffee, Rating.FOUR_RATED, "Nice hot cup of coffee!");
        coffee = manager.reviewProduct(coffee, Rating.FIVE_RATED, "Love coffee!");
        coffee = manager.reviewProduct(coffee, Rating.THREE_RATED, "That is all right I guess.");
        manager.printProductReport(coffee);

        Product cake = manager.createProduct(103, "Cake", BigDecimal.valueOf(2.99), Rating.NOT_RATED, LocalDate.now().plus(Period.ofDays(2)));
        cake = manager.reviewProduct(cake, Rating.FOUR_RATED, "Nice hot cup of cake!");
        cake = manager.reviewProduct(cake, Rating.FIVE_RATED, "Love cake!");
        cake = manager.reviewProduct(cake, Rating.THREE_RATED, "That is all right I guess.");
        manager.printProductReport(cake);

        Product hotChocolate = manager.createProduct(104, "Hot chocolate", BigDecimal.valueOf(3.99), Rating.NOT_RATED);
        hotChocolate = manager.reviewProduct(hotChocolate, Rating.FIVE_RATED, "Love hot chocolate!");
        hotChocolate = manager.reviewProduct(hotChocolate, Rating.ONE_RATED, "Hate it.");
        hotChocolate = manager.reviewProduct(hotChocolate, Rating.TWO_RATED, "Green hot chocolate, burk!");
        hotChocolate = manager.reviewProduct(hotChocolate, Rating.THREE_RATED, "That is all right I guess.");
        manager.printProductReport(hotChocolate);
        
        Product chocolate = manager.createProduct(105, "Chocolate", BigDecimal.valueOf(3.99), Rating.NOT_RATED, LocalDate.now().plus(Period.ofDays(3)));
        chocolate = manager.reviewProduct(chocolate, Rating.ONE_RATED, "Hate it.");
        chocolate = manager.reviewProduct(chocolate, Rating.TWO_RATED, "Green chocolate, burk!");
        manager.printProductReport(chocolate);
        
    }
}
