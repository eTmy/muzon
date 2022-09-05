package com.etmy.onlinerpg.location;

public class StartRoom extends Location {
    private static final String NAME = "Стартовая комната";
    private static final String TEXT = " Вы приземлились на планету Боннасис уже под вечер. " +
            "Служба безопасности провела недолгую проверку, по ходу дела осведомившись о цели вашего прибытия и изъяв " +
            "ваше оружие на время пребывания здесь. Выйдя из космопорта, вы отметили, что здесь довольно тепло, " +
            "несмотря на северные ветра. Пара минут езды на аэротакси - и вы уже в красивом холле дорогого отеля. " +
            "Клерк вручил вам обещанные 1200 рокнроллеров и дал электронный ключ от номера, оплаченного правительством " +
            "планеты Земля.\n" +
            " \n" +
            " Зайдя в номер, вы от усталости сразу плюхнулись на мягкую постель и уснули, пропустив вечерний ужин, " +
            "что было вам несвойственно. Впереди был тяжелый день...\n" +
            " \n" +
            " Незаметно наступило утро, и яркие лучи местного солнца осветили комнату. ";

    {
        this.setName(NAME);
        this.setQuestText(TEXT);
        this.actions.add("В сад");
        this.actions.add("Плюнуть на все и уехать отсюда");
    }

    @Override
    public void doAction(String action) {
        switch (action) {
            case "В сад" : move(action);
        }
    }

    @Override
    public Location move(String locationName) {
        switch (locationName) {
            case "В сад" : return new Garden();
            default : return null;
        }
    }
}
