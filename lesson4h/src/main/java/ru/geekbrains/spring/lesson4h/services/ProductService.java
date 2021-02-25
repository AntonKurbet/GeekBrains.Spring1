package ru.geekbrains.spring.lesson4h.services;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.lesson4h.exceptions.ResourceAllreadyExistException;
import ru.geekbrains.spring.lesson4h.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.lesson4h.models.Product;
import ru.geekbrains.spring.lesson4h.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProduct(int id) {
        Optional<Product> p = productRepository.getProduct(id);
        if (!p.isPresent()) throw new ResourceNotFoundException(String.format("product %d not found", id));
        return  p;
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public void addProduct(Product p) throws ResourceAllreadyExistException {
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
