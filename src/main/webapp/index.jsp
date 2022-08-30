<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Текстовый квест - Muzon</title>
    <link rel="stylesheet" href="static/main.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
<div id="root">
    <div class="game-root">
        <div class="mainArea">
            <div style="width: 60%;">
                <div class="questText">
                    ${game.player.currentLocation.questText}
                </div>
                <div class="questAction">
                    <c:forEach var = "action" items="${game.player.currentLocation.actions}">
                        <div onclick="window.location=`/location?location=${currentLocation}&action=${action}`"> * ${action}</div>
                    </c:forEach>
                </div>
            </div>
            <div class="questState">
                <div class="stateRaw" style="min-height: 32%">
                    ${game.timesOfDay.toString()}
                    <br>
                    Дней до концерта: ${game.daysLeft}
                    <br>
                    Деньги: ${game.player.money} рокнроллеров
                    <br>
                    Настроение: ${game.player.mood.toString()}
                    <br>
                    Вы ${game.player.drink.toString()}
                </div>
                <div class="stateRaw" style="min-height: 32%">
                    <div style="align-items: center">Группа</div>
                    <br>
                    Вокалист: ${game.vocal.toString()}
                    <br>
                    Гитарист: ${game.guitar.toString()}
                    <br>
                    Клавишник: ${game.keyboard.toString()}
                    <br>
                    Басист: ${game.bass.toString()}
                    <br>
                    Барабанщик: ${game.drum.toString()}
                </div>
                <div class="stateRaw" style="min-height: 32%">
                    Инвентарь
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


