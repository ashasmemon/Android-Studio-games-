package gamecentre.cardmatching;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;

/**
 * Process taps of tiles and undo throughout game play.
 */
class MatchingMovementController extends Observable {

    private MatchingBoardManager boardManager = null;

    /**
     * The MovementController for this activity.
     */
    MatchingMovementController() {
    }

    /**
     * Set the boardManager for this MovementController to manage.
     *
     * @param boardManager The current BoardManager
     */
    void setBoardManager(MatchingBoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Process when a tap is made
     *
     * @param context  used to make a Toast
     * @param position position of the tap
     */
    void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            boardManager.updateMoves();

            boardManager.touchMove(position);
            if (boardManager.isWin()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                MatchingScoreboard.setUser(MatchingScoreboardActivity.user);
                MatchingScoreboard.setNumMoves(boardManager.getNumMoves());
                // MatchingScoreboard.setBoardSize(boardManager.getBoardSize());
                setChanged();
                notifyObservers();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}