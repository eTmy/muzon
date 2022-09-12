package com.etmy.onlinerpg.servlet;


import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.exception.AttributeNotFoundException;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {

    public static String extractLogin(HttpServletRequest req) {
        try {
            return getHttpSessionStringAttribute(req.getSession(), "login");
        } catch (AttributeNotFoundException exception) {
            return "";
        }
    }

    public static boolean sessionIsAuthorized(Application app, String login) {
        return !login.isEmpty() && app.isAuthorized(login);
    }

    public static Application extractApp(HttpServletRequest req) {
        ServletContext context = req.getServletContext();

        Object appAttribute = context.getAttribute("app");
        if (Application.class != appAttribute.getClass()) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return (Application) appAttribute;
    }

    public static void setResponseBody(HttpServletResponse resp, String body) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(body);
        out.flush();
    }

    private static String getHttpSessionStringAttribute(HttpSession session, String attribute) throws AttributeNotFoundException {
        Object objectAttribute = session.getAttribute(attribute);

        if (objectAttribute == null) {
            throw new AttributeNotFoundException("Not found \"" + attribute + "\" attribute in HttpSession");
        }

        if (String.class != objectAttribute.getClass()) {
            throw new RuntimeException("Failed parse HttpSession attribute to string: \"" + objectAttribute + "\n");
        }
        return (String) objectAttribute;
    }

}
