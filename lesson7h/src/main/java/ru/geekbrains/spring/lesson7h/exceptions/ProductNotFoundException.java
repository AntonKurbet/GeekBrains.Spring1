package ru.geekbrains.spring.lesson7h.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String msg) {
        super("Product not found:" + msg);
    }
}
