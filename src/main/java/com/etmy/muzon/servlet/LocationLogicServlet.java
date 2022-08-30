package com.etmy.muzon.servlet;

import com.etmy.muzon.Game;
import com.etmy.muzon.location.Location;
import org.apache.commons.lang3.StringUtils;

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
        HttpSession currentSession = req.getSession();

        Game game = extractGame(currentSession);

        //Создать локацию через фабрику
        //Получить локацию и вызвать абстрактный метод передав туда действие

        resp.sendRedirect("/index.jsp");
    }


    private Game extractGame(HttpSession currentSession) {
        Object gameAttribute = currentSession.getAttribute("game");
        if (Game.class != gameAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }

        return (Game) gameAttribute;
    }

    private String getSelectedLocation(HttpServletRequest req) {
        String location = req.getParameter("location");

        if (StringUtils.isBlank(location)) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return location;
    }
}
