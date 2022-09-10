package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.dto.StatisticInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticServlet", value = "/statistic")
public class StatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = ServletUtils.extractLogin(req);

        StatisticInfo statisticInfo = StatisticInfo.builder()
                .username(login)
                .hp(100)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        ServletUtils.setResponseBody(resp, mapper.writeValueAsString(statisticInfo));
    }
}
