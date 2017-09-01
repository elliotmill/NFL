package com.android.elliotmiller.nfl.game;

/**
 * Created by azeezolaniran on 01/09/2017.
 */

public class GameQuarter {
    public final int TEAM_ONE_POINT;
    public final int TEAM_TWO_POINT;

    GameQuarter(int t1P, int t2P) {
        super();
        this.TEAM_ONE_POINT = t1P;
        this.TEAM_TWO_POINT = t2P;
    }

    public static GameQuarter newInstance(int p1, int p2) {
        return new GameQuarter(p1, p2);
    }
}
