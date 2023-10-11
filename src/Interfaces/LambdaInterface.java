package Interfaces;

import Order.Order;

import java.util.function.Predicate;


    @FunctionalInterface
    public interface LambdaInterface extends Predicate<Order> {
        boolean test(Order order);


    }

