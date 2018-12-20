package gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gamecentre.battlegame.BattleStartingActivity;
import gamecentre.cardmatching.MatchingScoreboardActivity;
import gamecentre.cardmatching.MatchingStartingActivity;
import gamecentre.slidingtiles.R;
import gamecentre.slidingtiles.SlidingtilesScoreboardActivity;
import gamecentre.slidingtiles.SlidingtilesStartingActivity;

/**
 * Allow the user to sign up.
 */
public class SignUpActivity extends AppCompatActivity {

    /**
     * The filename to save users to.
     */
    public static final String USER_FILENAME = "user_file.ser";

    /**
     * The user manager.
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();

        setContentView(R.layout.activity_signup);
        addSignUpButtonListener();
    }

    /**
     * Create a new user if username and password are nonempty and the username is not taken.
     */
    private void createUser() {
        EditText usernameEditText = findViewById(R.id.UsernameSignUp);
        EditText passwordEditText = findViewById(R.id.PasswordSignUp);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();
        } else if (userManager.getPassword(username) == null) {
            userManager.addUser(username, password);
            setFileNames(username);
            saveToFile();
            SlidingtilesScoreboardActivity.user = username;
            MatchingScoreboardActivity.user = username;
            switchToGameChoiceActivity();
        } else {
            Toast.makeText(this, "Username taken", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Set the filesnames for the user.
     *
     * @param username the name of the user.
     */
    private void setFileNames(String username) {
        SlidingtilesStartingActivity.saveFileName = username + "_slidingtiles.ser";
        SlidingtilesStartingActivity.autoSaveFileName = username + "_slidingtiles_autosave.ser";
        SlidingtilesStartingActivity.tempSaveFileName = username + "_slidingtiles_tmp.ser";
        BattleStartingActivity.saveFileName = username + "_battlegame.ser";
        BattleStartingActivity.autoSaveFileName = username + "_battlegame_autosave.ser";
        BattleStartingActivity.tempSaveFileName = username + "_battlegame_tmp.ser";
        MatchingStartingActivity.matchingSaveFileName = username + "_matching.ser";
        MatchingStartingActivity.matchingAutoSaveFileName = username + "_matching_autosave.ser";
        MatchingStartingActivity.matchingTempSaveFileName = username + "_matching_tmp.ser";
    }

    /**
     * Activate the ok button.
     */
    private void addSignUpButtonListener() {
        Button startButton = findViewById(R.id.okup);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile();
                createUser();
            }
        });
    }

    /**
     * Display the game choice activity.
     */
    private void switchToGameChoiceActivity() {
        Intent tmp = new Intent(this, GameChoiceActivity.class);
        startActivity(tmp);
    }

    /**
     * Load the user manager from USER_FILENAME.
     */
    private void loadFromFile() {
        try {
            InputStream inputStream = this.openFileInput(USER_FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                userManager = (UserManager) input.readObject();
                if (userManager == null) {
                    userManager = new UserManager();
                }
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
     * Save the user manager to USER_FILENAME.
     */
    public void saveToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(USER_FILENAME, MODE_PRIVATE));
            outputStream.writeObject(userManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

