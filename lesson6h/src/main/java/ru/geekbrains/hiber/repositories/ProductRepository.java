package ru.geekbrains.hiber.repositories;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.hiber.entities.Customer;
import ru.geekbrains.hiber.entities.Order;
import ru.geekbrains.hiber.entities.OrderDetails;
import ru.geekbrains.hiber.entities.Product;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private SessionFactory factory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createProduct(String title, Long cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = new Product(title, cost);
            session.save(p);
            session.getTransaction().commit();
        }
    }

    public Product getProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.getTransaction().commit();
            return p;
        }
    }

    public void updateProduct(long id, String title, Long cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            p.setTitle(title);
            p.setCost(cost);
            session.getTransaction().commit();
        }
    }

    public  void deleteProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }

    public List<Order> getProductOrderListById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            List<OrderDetails> odl = p.getOrderDetails();
            List<Order> ol = new ArrayList<>();
            for (OrderDetails od : odl) {
                if (ol.contains(od.getOrder())) continue;
                ol.add(od.getOrder());
            }
            session.getTransaction().commit();
            return ol;
        }
    }

    public List<Customer> getProductCustomerListById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            List<OrderDetails> odl = p.getOrderDetails();
            List<Customer> cl = new ArrayList<>();
            for (OrderDetails od : odl) {
                    if (cl.contains(od.getOrder().getCustomer())) continue;
                    cl.add(od.getOrder().getCustomer());
            }
            session.getTransaction().commit();
            return cl;
        }
    }
}
