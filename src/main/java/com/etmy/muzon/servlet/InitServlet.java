package com.etmy.muzon.servlet;

import com.etmy.muzon.Game;
import com.etmy.muzon.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        initGameSettings(currentSession);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }


    private void initGameSettings(HttpSession currentSession) {
        Game game = new Game();
        //Player player = game.getPlayer();

        currentSession.setAttribute("game", game);

//        currentSession.setAttribute("daysLeft", game.getDaysLeft());
//        currentSession.setAttribute("timesOfDay", game.getTimesOfDay().toString());
//        currentSession.setAttribute("vocalLevel", game.getVocal().toString());
//        currentSession.setAttribute("guitarLevel", game.getGuitar().toString());
//        currentSession.setAttribute("keyboardLevel", game.getKeyboard().toString());
//        currentSession.setAttribute("bassLevel", game.getBass().toString());
//        currentSession.setAttribute("drumLevel", game.getDrum().toString());
//
//        currentSession.setAttribute("questText", player.getCurrentLocation().getQuestText());
//        currentSession.setAttribute("mood", player.getMood().toString());
//        currentSession.setAttribute("money", player.getMoney());
//        currentSession.setAttribute("drinkStatus", player.getDrink().toString());
//
//        currentSession.setAttribute("currentLocation", player.getCurrentLocation().getName());
//        currentSession.setAttribute("actions", player.getCurrentLocation().getActions());
    }
}
