package gamecentre.battlegame;

import gamecentre.Score;
import gamecentre.Scoreboard;

public class BattleScoreboard extends Scoreboard {
    /**
     * The current user, number of moves, and hp of both characters
     */
    private static String user;
    private static int numMoves;
    private static int playerHpLost;
    private static int opponentHpLost;

    public static void setUser(String user) {
        BattleScoreboard.user = user;
    }

    static void setNumMoves(int numMoves) {
        BattleScoreboard.numMoves = numMoves;
    }

    static void setPlayerHpLost(int playerHpLost) {
        BattleScoreboard.playerHpLost = playerHpLost;
    }

    static void setOpponentHpLost(int opponentHpLost) {
        BattleScoreboard.opponentHpLost = opponentHpLost;
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
    protected void update() {
        if (numMoves != 0) {
            int points = (100 + (playerHpLost - opponentHpLost)) * numMoves;
            currentScore = new Score(user, points);
            updateGameHighScore(currentScore);
            updateUserHighScore(currentScore);
        }
    }
}
