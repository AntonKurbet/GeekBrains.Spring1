package ru.geekbrains.spring.lesson2h;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.geekbrains.spring.lesson2h")
public class AppConfig {
    @Autowired
    private ProductService productService;
}
