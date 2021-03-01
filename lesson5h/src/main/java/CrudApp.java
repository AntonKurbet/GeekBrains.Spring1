import entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutdown() {
        factory.close();
    }

    public static void createProduct(String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = new Product(title, cost);
            session.save(p);
            session.getTransaction().commit();
        }
    }

    public static Product getProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.getTransaction().commit();
            return p;
        }
    }

    public static void updateProduct(long id, String title, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            p.setTitle(title);
            p.setCost(cost);
            session.getTransaction().commit();
        }
    }

    public static void deleteProduct(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> list;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            list = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            init();
            System.out.println(getAllProducts());
            createProduct("Car",10000);
            System.out.println(getProduct(6));
            updateProduct(6,"SuperCar", 20000);
            System.out.println(getProduct(6));
            deleteProduct(6);
            System.out.println(getAllProducts());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
