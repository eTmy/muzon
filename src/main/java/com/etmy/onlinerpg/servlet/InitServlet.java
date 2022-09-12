package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Account;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();

        HttpSession session = req.getSession(true);
        ObjectMapper mapper = new ObjectMapper();
        User user;

        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);

        if (login.isEmpty() || !app.isAuthorized(login)) {
            Account account = mapper.readValue(getAccount(req), Account.class);

            user = new User(account);
            GameSession gameSession = new GameSession(user);

            app.getGameSessions().put(account.getLogin(), gameSession);
        } else {
            GameSession gameSession = app.getGameSession(login);
            user = gameSession.getUser();
        }

        session.setAttribute("login", user.getAccount().getLogin());

        resp.setStatus(301);
        resp.sendRedirect("/onlinerpg/index.html");
    }

    private String getAccount(HttpServletRequest req) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return jb.toString();
    }


}
