package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.abstraction.Creature;
import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "DialogServlet", value = "/dialog")

public class DialogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);
        String npcName = ServletUtils.getRequestParameter(req, "npcName");

        int messageId = Integer.parseInt(ServletUtils.getRequestParameter(req, "messageId"));

        if (messageId == 0) {
            return;
        }

        GameSession gameSession = app.getGameSession(login);
        Location currentLocation = gameSession.getUser().getCurrentLocation();

        Optional<Creature> creature = currentLocation.getCreatures().stream()
                .filter(c -> c.getName().equals(npcName))
                .findAny();

        if (creature.isEmpty()) {
            resp.sendError(500, "Not found npc " + npcName);
            return;
        }

        Npc npc = (Npc) creature.get();
        Message message = npc.speak(messageId);

        ObjectMapper mapper = new ObjectMapper();
        ServletUtils.setResponseBody(resp, mapper.writeValueAsString(message));
    }

}
