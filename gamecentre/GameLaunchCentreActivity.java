package gamecentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gamecentre.slidingtiles.R;

/**
 * The initial activity for the game centre.
 */
public class GameLaunchCentreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchcentre);

        addSignUpButtonListener();
        addSignInButtonListener();
    }

    /**
     * Create a sign up button.
     */
    private void addSignUpButtonListener() {
        Button startButton = findViewById(R.id.SignUp);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignUp();
            }
        });
    }

    /**
     * Create a sign in button.
     */
    private void addSignInButtonListener() {
        Button startButton = findViewById(R.id.SignIn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignIn();
            }
        });
    }

    /**
     * Switch to the sign up activity.
     */
    private void switchToSignUp() {
        Intent tmp = new Intent(this, SignUpActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the sign in activity.
     */
    private void switchToSignIn() {
        Intent tmp = new Intent(this, SignInActivity.class);
        startActivity(tmp);
    }
}
