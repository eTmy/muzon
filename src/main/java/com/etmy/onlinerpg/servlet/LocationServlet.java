package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.dto.LocationInfo;
import com.etmy.onlinerpg.exception.AttributeNotFoundException;
import com.etmy.onlinerpg.services.LocationFactoryImpl;
import com.etmy.onlinerpg.abstraction.Location;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

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
        String location = getSelectedLocation(req);
        //проверить если возможность перейти из текущей локации

        LocationFactoryImpl locationFactory = new LocationFactoryImpl();
        Location newLocation =  locationFactory.createLocation(location);

        LocationInfo locationInfo = LocationInfo.builder()
                .name(newLocation.getName())
                .text(newLocation.getText())
                .srcImage(newLocation.getSrcImage())
                .locations(newLocation.getLocations())
                .creatures(newLocation.getCreatures())
                .build();

        gameSession.getUser().setCurrentLocation(newLocation);

        ObjectMapper mapper = new ObjectMapper();

        ServletUtils.setResponseBody(resp, mapper.writeValueAsString(locationInfo));
    }


    private String getSelectedLocation(HttpServletRequest req) {
        String location = req.getParameter("location");

        if (StringUtils.isBlank(location)) {
            throw new AttributeNotFoundException("Request not found parameter \"location\n");
        }

        return location;
    }

    private String getSelectedAction(HttpServletRequest req) {
        String action = req.getParameter("action");

        if (StringUtils.isBlank(action)) {
            throw new AttributeNotFoundException("Request not found parameter \"action\n");
        }

        return action;
    }
}
