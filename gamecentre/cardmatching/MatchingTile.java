package gamecentre.cardmatching;

import android.support.annotation.NonNull;

import java.io.Serializable;

import gamecentre.slidingtiles.R;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class MatchingTile implements Comparable<MatchingTile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The unique id.
     */
    private int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id         the id
     * @param background the background
     */
    MatchingTile(int id, int background) {
        this.id = id;
        this.background = background;
    }

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId the ID of the tile
     */
    MatchingTile(int backgroundId) {
        id = backgroundId + 1;
        // This looks so ugly.
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.card_1;
                id = 1;
                break;
            case 2:
                background = R.drawable.card_2;
                id = 2;
                break;
            case 3:
                background = R.drawable.card_3;
                id = 3;
                break;
            case 4:
                background = R.drawable.card_4;
                id = 4;
                break;
            case 5:
                background = R.drawable.card_5;
                id = 5;
                break;
            case 6:
                background = R.drawable.card_6;
                id = 6;
                break;
            case 7:
                background = R.drawable.card_7;
                id = 7;
                break;
            case 8:
                background = R.drawable.card_8;
                id = 8;
                break;
            case 9:
                background = R.drawable.card_1;
                id = 1;
                break;
            case 10:
                background = R.drawable.card_2;
                id = 2;
                break;
            case 11:
                background = R.drawable.card_3;
                id = 3;
                break;
            case 12:
                background = R.drawable.card_4;
                id = 4;
                break;
            case 13:
                background = R.drawable.card_5;
                id = 5;
                break;
            case 14:
                background = R.drawable.card_6;
                id = 6;
                break;
            case 15:
                background = R.drawable.card_7;
                id = 7;
                break;
            case 16:
                background = R.drawable.card_8;
                id = 8;
                break;
            case 17:
                background = R.drawable.card_unknown;
            case 18:
                background = R.drawable.tile_25;
        }
    }
    @Override
    public int compareTo(@NonNull MatchingTile o) {
        return o.id - this.id;
    }

}