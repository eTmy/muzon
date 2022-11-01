package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements javax.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        Application app = new Application();

        context.setAttribute("app", app);
    }

}
