package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.Account;
import com.etmy.onlinerpg.Application;
import com.etmy.onlinerpg.GameSession;
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

        Application app = extractApp(context);
        GameSession gameSession = new GameSession();

        Account account = mapper.readValue(getAccount(req), Account.class);

        app.getGameSessions().put(account, gameSession);

        session.setAttribute("login", account.getLogin());
        context.setAttribute("app", app);

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

    private Application extractApp(ServletContext context) {
        Object appAttribute = context.getAttribute("app");
        if (Application.class != appAttribute.getClass()) {
            throw new RuntimeException("Request received an invalid parameter");
        }

        return (Application) appAttribute;
    }

}
