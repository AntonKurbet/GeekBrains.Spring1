package ru.geekbrains.spring.lesson2h;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.spring.lesson1h.Product;


import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);

        Scanner sc = new Scanner(System.in);

        boolean quit = false;

        while (!quit) {
            System.out.println("Input your command:\n(add,get,get_all,update,delete,quantity,avg_cost,quit");

            String cmd = sc.nextLine();
            String[] params = cmd.split(" ");

            try {
                switch (params[0]) {
                    case "/add":
                        if (params.length < 4) {
                            System.out.println("need ID TITLE COST");
                            break;
                        }
                        productService.addProduct(new Product(Integer.parseInt(params[1]),
                                params[2], Float.parseFloat(params[3])));
                        System.out.println("OK");
                        break;
                    case "/get":
                        if (params.length < 2) {
                            System.out.println("need ID");
                            break;
                        }
                        System.out.println(productService.getProduct(Integer.parseInt(params[1])));
                        break;
                    case "/get_all":
                        System.out.println(productService.getProducts().toString().replace("), ",")\n"));
                        break;
                    case "/update":
                        if (params.length < 4) {
                            System.out.println("need ID TITLE COST");
                            break;
                        }
                        productService.updateProduct(Integer.parseInt(params[1]), params[2],
                                Float.parseFloat(params[3]));
                        System.out.println("OK");
                        break;
                    case "/delete":
                        if (params.length < 2) {
                            System.out.println("need ID");
                            break;
                        }
                        productService.deleteProduct(Integer.parseInt(params[1]));
                        System.out.println("OK");
                        break;
                    case "/quantity":
                        System.out.println(productService.calculateQuantity());
                        break;
                    case "/avg_cost":
                        System.out.println(productService.calculateAverageCost());
                        break;
                    case "/quit":
                        System.out.println("Good bye!");
                        quit = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        context.close();
    }
}
