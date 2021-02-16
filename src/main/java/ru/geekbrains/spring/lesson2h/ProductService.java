package ru.geekbrains.spring.lesson2h;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.lesson1h.Product;
import ru.geekbrains.spring.lesson2c.Student;
import ru.geekbrains.spring.lesson2c.StudentRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(int id) {
        return productRepository.getProduct(id).orElse(null);
    }

    public List<Product> getProducts() {
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
