package gamecentre.slidingtiles;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;
import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    static int numRows = 4;

    /**
     * The number of rows.
     */
    static int numCols = 4;

    /**
     * Variable to handle user's desired board size input. Used to change numRows and numCols
     * if user starts new game.
     */
    public static int size = 4;

    /**
     * The tiles on the board in row-major order.
     */
     Tile[][] tiles = new Tile[numRows][numCols];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.numRows; row++) {
            for (int col = 0; col != Board.numCols; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return numCols * numRows;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile tempTile1 = new Tile(tiles[row1][col1].getId(), tiles[row1][col1].getBackground());
        Tile tempTile2 = new Tile(tiles[row2][col2].getId(), tiles[row2][col2].getBackground());
        tiles[row1][col1] = tempTile2;
        tiles[row2][col2] = tempTile1;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    /**
     * A iterator for board.
     */
    private class BoardIterator implements Iterator<Tile> {
        int nextRowIndex = 0;
        int nextColIndex = 0;

        @Override
        public boolean hasNext() {
            return nextRowIndex != numRows - 1 || nextColIndex != numCols - 1;
        }

        @Override
        public Tile next() {
            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Tile [%s][%s] is out of range", nextRowIndex, nextColIndex));
            }
            Tile result = tiles[nextRowIndex][nextColIndex];
            if (nextColIndex == numCols - 1) {
                nextColIndex = 0;
                nextRowIndex++;
            } else {
                nextColIndex++;
            }
            return result;
        }
    }
}
