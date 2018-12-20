package gamecentre.slidingtiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import gamecentre.Score;
import gamecentre.Scoreboard;

/**
 * Manage the scores the gamecentre.slidingtiles game.
 */
public class SlidingtilesScoreboard extends Scoreboard implements Serializable {

    /**
     * The current user, number of moves, and board size
     */
    private static String user;
    private static int numMoves;
    private static int boardSize;

    /**
     * The user's current score
     */

    static void setUser(String user) {
        SlidingtilesScoreboard.user = user;
    }

    static void setNumMoves(int numMoves) {
        SlidingtilesScoreboard.numMoves = numMoves;
    }

    static void setBoardSize(int boardSize) {
        SlidingtilesScoreboard.boardSize = boardSize;
    }

    String getUserBestScore() {
        return super.getUserBestScore(user);
    }

    protected String getUserCurrentScore() {
        return super.getUserCurrentScore();
    }

    protected void updateGameHighScore(Score newScore) {
        super.updateGameHighScore(newScore);
    }

    protected void updateUserHighScore(Score newScore) {
        super.updateUserHighScore(newScore);
    }

    @Override
    public void update() {
        if (numMoves != 0) {
            int points = numMoves / boardSize;
            currentScore = new Score(user, points);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        }
    }
}
