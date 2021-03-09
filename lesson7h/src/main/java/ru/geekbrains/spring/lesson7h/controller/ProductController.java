package ru.geekbrains.spring.lesson7h.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.lesson7h.model.Product;
import ru.geekbrains.spring.lesson7h.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(@RequestParam Optional<Long> minCost, @RequestParam Optional<Long> maxCost) {
        return productService.getAll(minCost, maxCost);
    }

    @GetMapping("/find")
    public List<Product> getAll(@RequestParam Optional<String> titlePart) {
        return productService.getAllByNamePart(titlePart);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/title")
    public Product getByName(@RequestParam String title) {
        return productService.getByTitle(title);
    }

    @PostMapping
        public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
