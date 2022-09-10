package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.Application;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {

    public static String extractLogin(HttpServletRequest req){
        return getHttpSessionStringAttribute(req.getSession(), "login");
    }

    public static Application extractApp(ServletContext context) {
        Object appAttribute = context.getAttribute("app");
        if (Application.class != appAttribute.getClass()) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return (Application) appAttribute;
    }

    public static void setResponseBody(HttpServletResponse resp, String body) throws IOException{
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(body);
        out.flush();
    }

    private static String getHttpSessionStringAttribute(HttpSession session, String attribute) {
        Object objectAttribute = session.getAttribute(attribute);
        if (String.class != objectAttribute.getClass()) {
            throw new RuntimeException("Failed parse HttpSession attribute to string: " + objectAttribute);
        }
        return (String) objectAttribute;
    }

}
