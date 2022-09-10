package com.etmy.onlinerpg.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GameSessionServlet", value = "/gamesession")
public class GameSessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();


        //String jsonGameSession = mapper.writeValueAsString(gameSession);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print("test");
        out.flush();
    }

    private String getGameSession(HttpServletRequest req) {
        String gameSession = req.getParameter("login");

        if (StringUtils.isBlank(gameSession)) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return gameSession;
    }
}
