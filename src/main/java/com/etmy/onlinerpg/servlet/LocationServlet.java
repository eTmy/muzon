package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.dto.LocationInfo;
import com.etmy.onlinerpg.services.LocationFactoryImpl;
import com.etmy.onlinerpg.abstraction.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

        if (!ServletUtils.sessionIsAuthorized(app, login)) {
            resp.sendError(401);
            return;
        }

        GameSession gameSession = app.getGameSession(login);
        String location = ServletUtils.getRequestParameter(req, "location");

        //TODO проверить если возможность перейти из текущей локации

        LocationFactoryImpl locationFactory = new LocationFactoryImpl();
        Location newLocation =  locationFactory.createLocation(location);
        newLocation.buildItems(gameSession.getUser());

        LocationInfo locationInfo = LocationInfo.builder()
                .name(newLocation.getName())
                .text(newLocation.getText())
                .srcImage(newLocation.getSrcImage())
                .locations(newLocation.getLocations())
                .creatures(newLocation.getCreatures())
                .items(newLocation.getItems())
                .build();

        gameSession.getUser().setCurrentLocation(newLocation);

        ObjectMapper mapper = new ObjectMapper();

        ServletUtils.setResponseBody(resp, mapper.writeValueAsString(locationInfo));
    }

}
