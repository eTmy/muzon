package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Application;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestListServlet", value = "/questlist")

public class QuestListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);

        ObjectMapper mapper = new ObjectMapper();

        ServletUtils.setResponseBody(resp, mapper.writeValueAsString(app.getGameSession(login).getUser().getQuests()));
    }
}
