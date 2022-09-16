package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.abstraction.Location;
import com.etmy.onlinerpg.abstraction.Npc;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.Quest;
import com.etmy.onlinerpg.exception.ParameterNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);

        if (!ServletUtils.sessionIsAuthorized(app, login)) {
            resp.sendError(401);
            return;
        }

        updateQuest(req, app, login);
    }

    public void updateQuest(HttpServletRequest req, Application app, String login) {
        String npcName = ServletUtils.getRequestParameter(req, "npcName");
        String questId = ServletUtils.getRequestParameter(req, "questId");

        GameSession gameSession = app.getGameSession(login);
        Location currentLocation = gameSession.getUser().getCurrentLocation();

        Npc npc = (Npc) currentLocation.getCreature(npcName);
        Quest quest = npc.getQuest(Integer.parseInt(questId));

        try {
            String isAccepted = ServletUtils.getRequestParameter(req, "isAccepted");
            quest.setAccepted(Boolean.parseBoolean(isAccepted));
        } catch (ParameterNotFoundException exception) {
            quest.setAccepted(false);
        }

        try {
            String isFinished = ServletUtils.getRequestParameter(req, "isFinished");
            quest.setAccepted(Boolean.parseBoolean(isFinished));
        } catch (ParameterNotFoundException exception) {
            quest.setFinished(false);
        }

        gameSession.getUser().addQuest(quest);
    }
}
