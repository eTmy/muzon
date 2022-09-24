package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.User;
import com.etmy.onlinerpg.dto.LocationInfo;
import com.etmy.onlinerpg.services.LocationFactoryImpl;
import com.etmy.onlinerpg.abstraction.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LocationServlet", value = "/location")
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);

        GameSession gameSession = app.getGameSession(login);
        User user = gameSession.getUser();
        String location = ServletUtils.getRequestParameter(req, "location");

        //TODO проверить если возможность перейти из текущей локации

        LocationFactoryImpl locationFactory = new LocationFactoryImpl();
        Location newLocation = locationFactory.createLocation(location);
        newLocation.buildItems(user);

        LocationInfo locationInfo = LocationInfo.builder()
                .name(newLocation.getName())
                .text(newLocation.getText())
                .srcImage(newLocation.getSrcImage())
                .locations(newLocation.getLocations())
                .creatures(newLocation.getCreatures())
                .items(newLocation.getItems())
                .build();

        user.setCurrentLocation(newLocation);

        ObjectMapper mapper = new ObjectMapper();

        Cookie cookie = new Cookie("currentRoom", user.getCurrentLocation().getName());
        resp.addCookie(cookie);

        ServletUtils.setResponseBody(resp, mapper.writeValueAsString(locationInfo));
    }

}
