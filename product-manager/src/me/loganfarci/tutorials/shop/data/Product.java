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
package me.loganfarci.tutorials.shop.data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;
import static me.loganfarci.tutorials.shop.data.Rating.*;

/**
 * {@code Product} class represents properties and behaviors of
 * product objects in the Product Management System.
 * <br>
 * Each product has an id, name and price.
 * <br>
 * Each product can have a discount, calculated based on a discount rate.
 * @version 4.0
 * @author Logan Farci
 */
public abstract class Product implements Rateable<Product> {

    /**
     * A constant that defines a
     * {@link java.math.BigDecimal BigDecimal} value of the discount rate
     * <br>
     * Discount rate is 10%
     */
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final Rating rating;

    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(int id, String name, BigDecimal price) {
        this(id, name, price, NOT_RATED);
    }

    public Product() {
        this(0, "no name", BigDecimal.ZERO);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    /**
     * Calculates discount based on a product price and discount rate.
     * @return a {@link java.math.BigDecimal BigDecimal}
     * value of the discount.
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }

    public LocalDate getBestBefore() { return LocalDate.now(); }

    @Override
    public String toString() {
        String priceText = NumberFormat.getCurrencyInstance().format(price);
        String discountText = NumberFormat.getCurrencyInstance().format(getDiscount());
        String format = "ID: %-4d Name: %-15s Price: %-10s Rating: %-5s Discount: %s Best before: %s";
        return String.format(format, id, name, priceText, rating.getStars(), discountText, getBestBefore());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Product) {
            final Product other = (Product) obj;
            return this.id == other.id && Objects.equals(this.name, other.name);
        }
        return false;
    }
}
