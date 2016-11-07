package CJPack;

import javax.servlet.*;

/**
 * Created by cj on 2016-11-02.
 */
public class WebContext implements ServletContextAttributeListener {

    private ServletContext context = null;


    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        context = servletContextAttributeEvent.getServletContext();
        System.out.println(servletContextAttributeEvent.getValue() + " Was created");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        context = servletContextAttributeEvent.getServletContext();
        System.out.println(servletContextAttributeEvent.getValue() + " Was removed");

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        context = servletContextAttributeEvent.getServletContext();
        System.out.println(servletContextAttributeEvent.getValue() + " Was replaced");

    }
}
