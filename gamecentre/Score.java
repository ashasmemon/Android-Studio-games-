package gamecentre;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Score implements Comparable<Score>, Serializable {
    /**
     * The user.
     */
    private String username;

    /**
     * The points the user has earned.
     */
    private int points;

    /**
     * The score for a user stored by their username and points.
     *
     * @param username The user's username
     * @param points   The points of this user
     */
    public Score(String username, int points) {
        this.username = username;
        this.points = points;
    }

    /**
     * Return the username
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the points
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Compares the two scores by the points
     *
     * @param other another score
     * @return This scores points minus the other scores points
     */
    @Override
    public int compareTo(@NonNull Score other) {
        return this.points - other.points;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Player %s: %d points", username, points);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return points == score.points &&
                Objects.equals(username, score.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, points);
    }
}
