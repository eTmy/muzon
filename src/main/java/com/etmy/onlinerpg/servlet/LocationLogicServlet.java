package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.location.ActionLocationFactory;
import com.etmy.onlinerpg.location.Location;
import com.etmy.onlinerpg.location.LocationFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LocationServlet", value = "/location")
public class LocationLogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        HttpSession session = req.getSession();

        String login = getGameSessionLogin(session);

        Application app = extractApp(context);

        String location = getSelectedLocation(req);
        String action = getSelectedAction(req);

        LocationFactory locationFactory = new ActionLocationFactory();

        Location currentLocation = locationFactory.createLocation(location);

        GameSession gameSession = app.getGameSessions().get(login);

        if(gameSession == null) {
            throw new NullPointerException("Login not found");
        }

        gameSession.getUser().setCurrentLocation(currentLocation.move(action));

        context.setAttribute("app", app);

        resp.sendRedirect("/index.html");
    }

    private Application extractApp(ServletContext context) {
        Object appAttribute = context.getAttribute("app");

        if (Application.class != appAttribute.getClass()) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return (Application) appAttribute;
    }

    private String getGameSessionLogin(HttpSession session) {
        String login = (String) session.getAttribute("login");

        if (StringUtils.isBlank(login)) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return login;
    }

    private String getSelectedLocation(HttpServletRequest req) {
        String location = req.getParameter("location");

        if (StringUtils.isBlank(location)) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return location;
    }

    private String getSelectedAction(HttpServletRequest req) {
        String action = req.getParameter("action");

        if (StringUtils.isBlank(action)) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return action;
    }
}
