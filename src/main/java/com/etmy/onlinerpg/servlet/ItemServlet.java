package com.etmy.onlinerpg.servlet;

import com.etmy.onlinerpg.abstraction.Item;
import com.etmy.onlinerpg.core.Application;
import com.etmy.onlinerpg.core.User;
import com.etmy.onlinerpg.item.ItemType;
import com.etmy.onlinerpg.item.weapon.Weapon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ItemServlet", value = "/item")
public class ItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Application app = ServletUtils.extractApp(req);
        String login = ServletUtils.extractLogin(req);

        if (!ServletUtils.sessionIsAuthorized(app, login)) {
            resp.sendError(401);
            return;
        }

        String json = ServletUtils.getReqBody(req);

        Item item = getItem(json);
        String action = getAction(json);
        User user = app.getGameSession(login).getUser();

        switch (action) {
            case "take" : user.takeItem(item);
            case "use" : user.useItem(item);
        }

    }


    //TODO убрать дублирующий код
    public Item getItem(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        ItemType itemType = mapper.treeToValue(node.path("item").path("type"), ItemType.class);

        switch (itemType) {
            case WEAPON: return mapper.treeToValue(node.get("item"), Weapon.class);
            default: throw new NullPointerException();
        }

    }

    public String getAction(String json) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        return node.path("action").asText();
    }
}
