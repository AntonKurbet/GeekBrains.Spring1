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
public class CustomerRepository {

    private SessionFactory factory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.factory = factory.unwrap(SessionFactory.class);
    }

    public void createCustomer(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = new Customer(name);
            session.save(c);
            session.getTransaction().commit();
        }
    }

    public Customer getCustomer(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            List<Order> ol = c.getOrders();
            session.getTransaction().commit();
            return c;
        }
    }

    public List<Order> getCustomerOrdersById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            List<Order> ol = new ArrayList<>();
            ol.addAll(c.getOrders());
            session.getTransaction().commit();
            return ol;
        }
    }

    public List<Product> getCustomerProductListById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            List<Order> ol = c.getOrders();
            List<Product> pl = new ArrayList<>();
            for (Order o : ol) {
                for (OrderDetails od : o.getOrderDetails()) {
                    if (pl.contains(od.getProduct())) continue;
                    pl.add(od.getProduct());
                }
            }
            session.getTransaction().commit();
            return pl;
        }
    }

    public void updateCustomer(long id, String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            c.setName(name);
            session.getTransaction().commit();
        }
    }

    public  void deleteCustomer(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            session.delete(c);
            session.getTransaction().commit();
        }
    }
}
