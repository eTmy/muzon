package com.etmy.muzon;

import com.etmy.muzon.status.MusicianLevel;
import com.etmy.muzon.status.TimesOfDay;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private Player player;
    private TimesOfDay timesOfDay;
    private int daysLeft;

    private MusicianLevel vocal;
    private MusicianLevel guitar;
    private MusicianLevel keyboard;
    private MusicianLevel bass;
    private MusicianLevel drum;

    public Game() {
        this.player = new Player();
        this.timesOfDay = TimesOfDay.MORNING;
        this.daysLeft = 3;

        this.vocal = MusicianLevel.MEDIUM;
        this.guitar = MusicianLevel.NO;
        this.keyboard = MusicianLevel.NO;
        this.bass = MusicianLevel.NO;
        this.drum = MusicianLevel.NO;
    }
}
