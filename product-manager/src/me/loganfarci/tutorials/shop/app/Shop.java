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

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code Shop} class represents an application that manages Products.
 * @version 4.0
 * @author Logan Farci
 */
public class Shop {

    private static final int MIN_PRODUCT_ID = 101;
    private static final int PRODUCTS_COUNT = 6;

    public static void main(String[] args) {
        AtomicInteger clientCount = new AtomicInteger(0);

        Callable<String> client = () -> {
            ProductManager manager = ProductManager.getInstance();
            String clientId = "Client " + clientCount.incrementAndGet();
            String threadName = Thread.currentThread().getName();
            int productId = ThreadLocalRandom.current().nextInt(PRODUCTS_COUNT - 1) + MIN_PRODUCT_ID;
            String languageTag = ProductManager.getSupportedLocales()
                    .stream()
                    .skip(ThreadLocalRandom.current().nextInt(7))
                    .findFirst().get();
            StringBuilder log = new StringBuilder();

            log.append(clientId + " " + threadName + "\n-\tstart of log\t-\n");

            log.append(manager.getDiscounts(languageTag)
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "\t" + entry.getValue())
                .collect(Collectors.joining("\n")));

            Product product = manager.reviewProduct(productId, Rating.FOUR_RATED, "Yet another review");
            log.append((product == null) ? "\nProduct " + productId + " not reviewed.\n" : "\nProduct " + productId + " reviewed.\n");
            manager.printProductReport(productId, languageTag, clientId);
            log.append(clientId + " generated report for " + productId + " product.");
            log.append("\n-\tend of log\t-\n\n");

            return log.toString();
        };

        List<Callable<String>> clients = Stream.generate(() -> client).limit(5).collect(Collectors.toList());
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            List<Future<String>> results = executorService.invokeAll(clients);
            executorService.shutdown();
            for (Future<String> result : results) {
                try {
                    System.out.printf(result.get());
                }catch (InterruptedException | ExecutionException e) {
                    Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error retrieving client log", e);
                }
            }
        } catch (InterruptedException e) {
            Logger.getLogger(Shop.class.getName()).log(Level.SEVERE, "Error invoking clients", e);
        }
    }
}