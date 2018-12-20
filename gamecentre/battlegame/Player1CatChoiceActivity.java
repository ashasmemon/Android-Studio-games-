package gamecentre.battlegame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import gamecentre.GameChoiceActivity;
import gamecentre.slidingtiles.R;

/**
 * Activity for choosing the cat to play.
 */
public class Player1CatChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlegame_p1_cat_character);

        addNinjaButtonListener();
        addSamuraiButtonListener();
        addShamanButtonListener();
        startAnimations();

    }

    /**
     * Start the animations in the activity.
     */
    private void startAnimations() {
        AnimationDrawable ninjaAnimation;
        ImageView ninjaImage = findViewById(R.id.animatedninja1);
        ninjaImage.setBackgroundResource(R.drawable.ninja_cat_animated);
        ninjaAnimation = (AnimationDrawable) ninjaImage.getBackground();
        ninjaAnimation.start();

        AnimationDrawable shamanAnimation;
        ImageView shamanImage = findViewById(R.id.animatedshaman1);
        shamanImage.setBackgroundResource(R.drawable.shaman_cat_animated);
        shamanAnimation = (AnimationDrawable) shamanImage.getBackground();
        shamanAnimation.start();

        AnimationDrawable samuraiAnimation;
        ImageView samuraiImage = findViewById(R.id.animatedsamurai1);
        samuraiImage.setBackgroundResource(R.drawable.samurai_cat_animated);
        samuraiAnimation = (AnimationDrawable) samuraiImage.getBackground();
        samuraiAnimation.start();
    }

    /**
     * Add the Shaman button.
     */
    private void addShamanButtonListener() {
        Button shamanButton = findViewById(R.id.shaman);
        shamanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchToPlayer2DogChoiceActivity("ShamanCat");
            }
        });
    }

    /**
     * Add the Samurai button.
     */
    private void addSamuraiButtonListener() {
        Button samuraiButton = findViewById(R.id.samurai);
        samuraiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPlayer2DogChoiceActivity("SamuraiCat");
            }
        });
    }

    /**
     * Add the Ninja button.
     */
    private void addNinjaButtonListener() {
        Button ninjaButton = findViewById(R.id.ninja);
        ninjaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPlayer2DogChoiceActivity("NinjaCat");
            }
        });
    }

    /**
     * Switch to the Player2DogChoiceActivity view.
     */
    private void switchToPlayer2DogChoiceActivity(String character) {
        Intent tmp = new Intent(this, Player2DogChoiceActivity.class);
        Bundle extras = new Bundle();
        extras.putString("player1", character);
        tmp.putExtras(extras);
        startActivity(tmp);
    }
}
