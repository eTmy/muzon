package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.core.Account;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.GameSession;
import com.etmy.onlinerpg.core.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user;

        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);

        if (ServletUtils.sessionIsAuthorized(app, login)) {
            //TODO сделать поиск по уже существующим юзерам в файле/бд и инициализировать сессию у сущ юзера
            GameSession gameSession = app.getGameSession(login);
            user = gameSession.getUser();
        } else {
            Account account = mapper.readValue(ServletUtils.getReqBody(req), Account.class);

            user = new User(account);
            GameSession gameSession = new GameSession(user);

            app.getGameSessions().put(account.getLogin(), gameSession);
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("login", user.getAccount().getLogin());

        Cookie cookie = new Cookie("currentRoom", user.getCurrentLocation().getName());

        resp.addCookie(cookie);
        resp.setStatus(301);
        resp.sendRedirect("/onlinerpg/index.html");
    }


}
