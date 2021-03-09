package ru.geekbrains.spring.lesson7h.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.lesson7h.model.Product;
import ru.geekbrains.spring.lesson7h.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product getByTitle(String title) {
        return productRepository.findProductByTitle(title);
    }

    public Product add(Product student) {
        return productRepository.save(student);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAll(Optional<Long> minCost, Optional<Long> maxCost) {
        if (minCost.isPresent()) {
            if (maxCost.isPresent()) {
                return productRepository.getAllByCostBetween(minCost.get(),maxCost.get());
            }
            return productRepository.getAllByCostGreaterThanEqual(minCost.get());
        } else if(maxCost.isPresent()) {
            return productRepository.getAllByCostIsLessThanEqual(maxCost.get());
        } else {
            return productRepository.findAll();
        }
    }

    public List<Product> getAllByNamePart(Optional<String> titlePart) {
        if (titlePart.isPresent())
            return productRepository.getProductByTitleContainsIgnoreCase(titlePart.get());
        else
            return productRepository.findAll();
    }
}
