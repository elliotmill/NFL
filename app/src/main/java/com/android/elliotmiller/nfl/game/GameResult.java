package com.android.elliotmiller.nfl.game;

/**
 * Created by azeezolaniran on 01/09/2017.
 */

public class GameResult {
    public final GameQuarter quarterOne, quarterTwo, quarterThree, quarterFour;

    public GameResult(GameQuarter gQ1, GameQuarter gQ2, GameQuarter gQ3, GameQuarter gQ4) {
        super();
        this.quarterOne = gQ1;
        this.quarterTwo = gQ2;
        this.quarterThree = gQ3;
        this.quarterFour = gQ4;
    }

    public int[] getFinalScores() {
        int t1Score = this.quarterFour.TEAM_ONE_POINT;
        int t2Score = this.quarterFour.TEAM_TWO_POINT;
        return new int[]{t1Score, t2Score};
    }
}
