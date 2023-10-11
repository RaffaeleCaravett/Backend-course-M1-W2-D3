package Interfaces;

import Product.Product;

import java.util.function.Predicate;

@FunctionalInterface
public interface CategoriesLambda extends Predicate<Product> {
    boolean test(Product product);


}
