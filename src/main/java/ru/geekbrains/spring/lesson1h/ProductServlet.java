package ru.geekbrains.spring.lesson1h;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i,"Product#" + i, (float) (Math.round(100000 * Math.random()) * 0.01)));
            log.info(productList.get(i).toString());
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("New request");
        resp.getWriter().println("Products:");
        for (int i = 0; i < 10; i++) {
            resp.getWriter().println(productList.get(i));
        }
    }
}
