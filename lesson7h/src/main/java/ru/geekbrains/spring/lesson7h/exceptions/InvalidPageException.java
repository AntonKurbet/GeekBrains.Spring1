package ru.geekbrains.spring.lesson7h.exceptions;

public class InvalidPageException extends RuntimeException{
    public InvalidPageException(String msg) {
        super("Invalid page:" + msg);
    }
}
