package gamecentre.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class SlidingtilesStartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static String saveFileName;
    /**
     * A temporary save file.
     */
    public static String tempSaveFileName;
    /**
     * The auto saved file.
     */
    public static String autoSaveFileName;
    /**
     * The board manager.
     */
    private BoardManager boardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = new BoardManager();
        saveToFile(tempSaveFileName);

        setContentView(R.layout.activity_slidingtiles_starting);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addBoardSizeListener();
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Board.numCols = Board.size;
                Board.numRows = Board.size;
                boardManager = new BoardManager();
                switchToGame();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile(saveFileName);
                saveToFile(tempSaveFileName);
                saveToFile(autoSaveFileName);
                makeToastLoadedText();
                switchToGame();
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(saveFileName);
                saveToFile(tempSaveFileName);
                makeToastSavedText();
            }
        });
    }

    /**
     * Activate the board size radio group.
     */
    private void addBoardSizeListener() {
        final RadioGroup radio = (RadioGroup) findViewById(R.id.Radio_Group);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                switch (index) {
                    case 0: // 3x3 case
                        Board.size = 3;
                        break;
                    case 1: // 4x4 case
                        Board.size = 4;
                        break;
                    case 2: // 5x5 case
                        Board.size = 5;
                        break;
                }
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the score board activity.
     */
    private void switchToScoreBoard() {
        Intent m = new Intent(this, SlidingtilesScoreboardActivity.class);
        startActivity(m);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        saveToFile(SlidingtilesStartingActivity.tempSaveFileName);
        startActivity(tmp);
    }

    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadFromFile(tempSaveFileName);
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            InputStream autoInputStream = this.openFileInput(autoSaveFileName);
            if (inputStream != null && inputStream == autoInputStream) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (BoardManager) input.readObject();
                inputStream.close();
            } else {
                if (autoInputStream != null) {
                    ObjectInputStream input = new ObjectInputStream(autoInputStream);
                    boardManager = (BoardManager) input.readObject();
                    autoInputStream.close();
                }
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
}

