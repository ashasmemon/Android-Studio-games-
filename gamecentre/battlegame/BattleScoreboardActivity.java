package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gamecentre.Score;
import gamecentre.slidingtiles.R;

public class BattleScoreboardActivity extends AppCompatActivity {

    public static final String SCORE_FILENAME = "battle_scoreboard_file.ser";
    public static String user;
    TextView scoreBoard;
    BattleScoreboard scoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);
        scoreBoard = findViewById(R.id.s_b);
        loadFromFile(SCORE_FILENAME);
        if (scoreboard == null) {
            scoreboard = new BattleScoreboard();
        }
        scoreboard.update();
        saveToFile(SCORE_FILENAME);
        scoreBoard.setText(scoreboard.toString());
        displayScore();
        displayBestScore();
        addMainButtonListener();
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(this, BattleStartingActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveToFile(SCORE_FILENAME);
    }

    /**
     * Load a preexisting ScoreBoard from a previous game.
     *
     * @param fileName name of file to load
     */
    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                scoreboard = (BattleScoreboard) input.readObject();
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
     * Save the current scoreBoard to file for later access.
     *
     * @param fileName name of the file to save
     */

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(scoreboard);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Display the user's score from the game that was just played.
     */
    private void displayScore() {
        TextView score = findViewById(R.id.score);
        score.setText(scoreboard.getUserCurrentScore());
    }

    /**
     * Display the user's high score for the game.
     */
    private void displayBestScore() {
        TextView highScore = findViewById(R.id.highScore);
        highScore.setText(scoreboard.getUserBestScore());
    }

    /**
     * Add the main button to go back to the main game screen.
     */
    private void addMainButtonListener() {
        Button BattleButton = findViewById(R.id.main);
        BattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToBattleActivity();
            }
        });
    }

    /**
     * Display the game's starting activity.
     */
    private void switchToBattleActivity() {
        Intent tmp = new Intent(this, BattleStartingActivity.class);
        startActivity(tmp);
    }
}
