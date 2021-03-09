package ru.geekbrains.spring.lesson7h.controller;

public enum SortOrder {
    ASC,DESC;

    private byte priority;

    SortOrder() {
        priority = 0;
    }

    SortOrder(byte priority) {
        this.priority = priority;
    }

    byte getPriority() {
        return priority;
    }
}
