package ru.geekbrains.spring.lesson3h.services;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.lesson3h.models.Product;
import ru.geekbrains.spring.lesson3h.repositories.ProductRepository;


import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(int id) {
        return productRepository.getProduct(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public void addProduct(Product p) {
        productRepository.addProduct(p);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public void updateProduct(int id, String name, float cost) {
        productRepository.updateProduct(id, name, cost);
    }

    public int calculateQuantity() {
        return productRepository.getProducts().size();
    }
    public float calculateAverageCost() {
        List<Product> products = productRepository.getProducts();
        if (products == null || products.size() == 0) {
            return 0;
        }

        int avg = 0;
        for (Product p : products) {
            avg += p.getCost();
        }
        avg /= products.size();
        return avg;
    }
}
