package ru.geekbrains.spring.lesson2h;

import ru.geekbrains.spring.lesson1h.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProducts();
    Optional<Product> getProduct(int id);
    void addProduct(Product product);
    void updateProduct(int id, String title, float cost);
    void deleteProduct(int id);
}
