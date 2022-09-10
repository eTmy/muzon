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
   <div class="gameSession-root">
        <div class="mainArea">
            <div style="width: 60%;">
                <div class="questText">
                    ${gameSession.player.currentLocation.questText}
                </div>
                <div class="questAction">
                    <c:forEach var = "action" items="${gameSession.player.currentLocation.actions}">
                        <div onclick="window.location=`/location?location=${gameSession.player.currentLocation.name}&action=${action}`"> * ${action}</div>
                    </c:forEach>
                    <button onclick="window.location=`/start?login=SuperDestroyer`"> Начать как SuperDestroyer </button>
                    <button onclick="window.location=`/start?login=RozovayaTyan`"> Начать как РоЗоВаЯ ТЯН </button>
                </div>
            </div>
            <div class="questState">
                <div class="stateRaw" style="min-height: 32%">
                    ${gameSession.timesOfDay.toString()}
                    <br>
                    Дней до концерта: ${gameSession.daysLeft}
                    <br>
                    Деньги: ${gameSession.player.money} рокнроллеров
                    <br>
                    Настроение: ${gameSession.player.mood.toString()}
                    <br>
                    Вы ${gameSession.player.drink.toString()}
                </div>
                <div class="stateRaw" style="min-height: 32%">
                    <div style="align-items: center">Группа</div>
                    <br>
                    Вокалист: ${gameSession.vocal.toString()}
                    <br>
                    Гитарист: ${gameSession.guitar.toString()}
                    <br>
                    Клавишник: ${gameSession.keyboard.toString()}
                    <br>
                    Басист: ${gameSession.bass.toString()}
                    <br>
                    Барабанщик: ${gameSession.drum.toString()}
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


