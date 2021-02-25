package ru.geekbrains.spring.lesson4h.exceptions;

public class ResourceAllreadyExistException extends RuntimeException {
    public ResourceAllreadyExistException (String message) {
        super(message);
    }
}
