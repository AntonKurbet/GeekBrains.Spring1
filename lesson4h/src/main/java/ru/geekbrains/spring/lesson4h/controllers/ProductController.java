package ru.geekbrains.spring.lesson4h.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.lesson4h.models.Product;
import ru.geekbrains.spring.lesson4h.services.ProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

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
