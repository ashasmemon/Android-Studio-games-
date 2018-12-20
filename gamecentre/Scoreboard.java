package gamecentre;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public abstract class Scoreboard implements Serializable {
    /**
     * Size of scoreboard (ie. how many top scores are stored and displayed).
     */
    public static final int LENGTH = 10;

    /**
     * An ordered list with the highest score as first item.
     */
    private Score[] scoreList = new Score[LENGTH];

    /**
     * A map of username to high scores.
     */
    private HashMap<String, Score> userToBestScore = new HashMap<>();

    /**
     * For testing only: get the scoreList
     *
     * @return the scoreList
     */
    public Score[] getScoreList() {
        return scoreList;
    }

    protected Score currentScore;

    public void setScoreList(Score[] scoreList) {
        this.scoreList = scoreList;
    }

    /**
     * For testing only: get the HashMap of userToBestScore
     *
     * @return the user to best score
     */
    public HashMap<String, Score> getUserToBestScore() {
        return userToBestScore;
    }

    /**
     * For testing only: set the userToBestScore dictionary.
     *
     * @param userToBestScore A dictionary of String user to Score score
     */
    public void setUserToBestScore(HashMap<String, Score> userToBestScore) {
        this.userToBestScore = userToBestScore;
    }

    /**
     * For testing only: set the current score.
     *
     * @param currentScore the score
     */
    public void setCurrentScore(Score currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * Gets the user's best score
     *
     * @param user The user
     * @return A string representation of the user's best score
     */
    protected String getUserBestScore(String user) {
        if (userToBestScore.get(user) == null) {
            return "Best Score: None";
        } else {
            return String.format(Locale.getDefault(), "Best Score: %d",
                    userToBestScore.get(user).getPoints());
        }
    }

    /**
     * Gets the user's current score
     *
     * @return A string representation of the user's current score
     */
    protected String getUserCurrentScore() {
        if (currentScore == null) {
            return "Your Score: None";
        } else {
            return String.format(Locale.getDefault(), "Your Score: %d", currentScore.getPoints());
        }
    }

    @Override
    public String toString() {
        String scores = "";
        for (int i = 0; i < LENGTH; i++) {
            // String looks like: "1. username: score \n 2. username: score ..."
            if (scoreList[i] == null) {
                // If there is no high score, string looks like: "1. \n 2. \n ..."
                scores = String.format(Locale.getDefault(), "%s%d.\n", scores, i + 1);
            } else {
                scores = String.format(Locale.getDefault(), "%s%d. %s\n", scores, i + 1,
                        scoreList[i].toString());
            }
        }
        return "SCORE BOARD\n\n" + scores;
    }

    /**
     * If the new score is a high score, update the list of high scores.
     *
     * @param newScore the newest score
     */
    protected void updateGameHighScore(Score newScore) {

        for (int i = LENGTH - 1; i >= 0; i--) {
            if (scoreList[i] == null || newScore.compareTo(scoreList[i]) < 0) {
                Score current = scoreList[i];
                scoreList[i] = newScore;
                if (i < LENGTH - 1) {
                    scoreList[i + 1] = current;
                }
            }
        }
    }


    /**
     * Update a user's high score if their new score is better.
     *
     * @param newScore the newest score
     */
    protected void updateUserHighScore(Score newScore) {
        String username = newScore.getUsername();
        if ((userToBestScore.get(username) == null) ||
                (userToBestScore.get(username).compareTo(newScore) > 0)) {
            userToBestScore.put(username, newScore);
        }
    }

    /**
     * Update the user high score and game high score.
     */
    protected abstract void update();
}
