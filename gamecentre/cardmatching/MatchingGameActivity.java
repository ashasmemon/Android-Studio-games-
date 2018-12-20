package gamecentre.cardmatching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import gamecentre.slidingtiles.CustomAdapter;
import gamecentre.slidingtiles.R;

public class MatchingGameActivity extends AppCompatActivity implements Observer{
    /**
     * The board manager.
     */
    private MatchingBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private MatchingGestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        loadFromFile(MatchingStartingActivity.matchingTempSaveFileName);
        createTileButtons(this);
        setContentView(R.layout.activity_cardmatching_main);

        // Add View to activity
        gridView = findViewById(R.id.matching_grid);
        gridView.setNumColumns(MatchingBoard.numCols);
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        gridView.mController.addObserver(new WinObserver());

        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / MatchingBoard.numCols;
                        columnHeight = displayHeight / MatchingBoard.numRows;

                        display();
                    }
                });
    }
    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        MatchingBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != MatchingBoard.numRows; row++) {
            for (int col = 0; col != MatchingBoard.numCols; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        MatchingBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / MatchingBoard.numRows;
            int col = nextPos % MatchingBoard.numCols;
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(MatchingStartingActivity.matchingTempSaveFileName);
        saveToFile(MatchingStartingActivity.matchingAutoSaveFileName);
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (MatchingBoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        saveToFile(MatchingStartingActivity.matchingAutoSaveFileName);
    }

    /**
     * Switches to the scoreboard activity.
     */
    private void switchToScoreBoardActivity() {
        Intent tmp = new Intent(this, MatchingScoreboardActivity.class);
        startActivity(tmp);
    }

    /**
     * An observer class created to observe if the game has been won. If it does, it will update and call switchToScoreboard.
     */
    private class WinObserver implements Observer {
        public void update(Observable o, Object arg) {
            switchToScoreBoardActivity();
        }
    }
}
