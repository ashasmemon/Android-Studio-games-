package gamecentre.battlegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import gamecentre.slidingtiles.R;

/**
 * Activity for choosing whether to play as a dog or as a cat.
 */
public class CatOrDogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_catsdogs);

        addCatButtonListener();
        addDogButtonListener();

    }

    /**
     * Add the dog button.
     */
    private void addDogButtonListener() {
        Button dogButton = findViewById(R.id.dogs);
        dogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToDogChoiceActivity();
            }
        });
    }


    /**
     * Add the cat button.
     */
    private void addCatButtonListener() {
        Button catButton = findViewById(R.id.cats);
        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToCatChoiceActivity();
            }
        });
    }

    /**
     * Switch to the Player1CatChoiceActivity view.
     */
    private void switchToCatChoiceActivity() {
        Intent tmp = new Intent(this, Player1CatChoiceActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the Player1DogChoiceActivity view.
     */
    private void switchToDogChoiceActivity() {
        Intent tmp = new Intent(CatOrDogActivity.this, Player1DogChoiceActivity.class);
        startActivity(tmp);
    }
}
