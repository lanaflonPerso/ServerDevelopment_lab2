package CJPack;

import org.hibernate.annotations.SourceType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cj on 2016-11-02.
 */
@WebServlet(description = "This is a samlt servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        increment();
        System.out.println("Test");
        String searchTerm = req.getParameter("searchTerm");
        decrement();
    }


    protected synchronized void increment() {
        count++;
    }

    protected synchronized void decrement() {
        count--;
    }

    protected synchronized int getCount() {
        return count;
    }
}
