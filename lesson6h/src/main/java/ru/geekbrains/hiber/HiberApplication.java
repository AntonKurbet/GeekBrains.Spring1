package ru.geekbrains.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.geekbrains.hiber.entities.Customer;
import ru.geekbrains.hiber.entities.Product;
import ru.geekbrains.hiber.repositories.CustomerRepository;
import ru.geekbrains.hiber.repositories.ProductRepository;

import java.util.List;

@SpringBootApplication
public class HiberApplication {

    private static CustomerRepository customerRepository;
    private static ProductRepository productRepository;

    private static ConfigurableApplicationContext context;


    public static void main(String[] args) {

        context = SpringApplication.run(HiberApplication.class, args);
        //customerTest();
        productTest();
    }

    private static void productTest() {
        productRepository = (ProductRepository) context.getBean("productRepository");
        //Product p = productRepository.getProduct(1);
        //System.out.println(p);
        List<Customer> cl = productRepository.getProductCustomerListById(3);
        System.out.println(cl);
    }

    private static void customerTest() {
        customerRepository = (CustomerRepository) context.getBean("customerRepository");

        //Customer c = customerRepository.getCustomer(1);
        //List<Order> ol = customerRepository.getCustomerOrdersById(1);
        List<Product> pl = customerRepository.getCustomerProductListById(2);
        //System.out.println(c);
        //System.out.println(ol);
        System.out.println(pl);
    }

//    public static void main(String[] args) {
//        SpringApplication.run(HiberApplication.class, args);
//    }

}
