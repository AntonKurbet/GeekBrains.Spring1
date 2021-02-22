package ru.geekbrains.spring.lesson3h.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.spring.lesson3h.models.Product;
import ru.geekbrains.spring.lesson3h.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("frontProducts", productService.getAllProducts());
        return "all_products";
    }

    @GetMapping("/remove/{id}")
    public String deleteBoxById(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products/all";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        if (product != null)
            productService.addProduct(product);
        return "redirect:/products/all";
    }
}
