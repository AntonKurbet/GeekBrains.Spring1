package ru.geekbrains.spring.lesson3h.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.lesson3h.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductInMemoryRepository implements ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(new Product(i,
                    "Product#" + i,
                    (float) (Math.round(100000 * Math.random()) * 0.01)));
        }
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product) {
        if (getProduct(product.getId()).isPresent())
            throw new RuntimeException("This id already used");
        products.add(product);
    }

    public Optional<Product> getProduct(int id) {
        return products.stream().parallel().filter(p -> p.getId() == id).findAny();
    }

    public void updateProduct(int id, String title, float cost) {
        Optional<Product> pr = getProduct(id);
        if (!pr.isPresent())
            throw new RuntimeException("This id not found");
        pr.ifPresent(p -> {p.setTitle(title); p.setCost(cost);});
    }

    public void deleteProduct(int id) {
        if (!getProduct(id).isPresent())
            throw new RuntimeException("This id not found");
        products.removeIf(p -> p.getId() == id);
    }
}
