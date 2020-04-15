package project2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/***********************************************************************
 * The NumberGameArrayList class contains methods for 1024 game mechanics.
 *
 * @author (Kyle Jacobson)
 * @version (09/25/28)
 ***********************************************************************/

public class NumberGameArrayList implements NumberSlider {

    /** int for winning value that must be achieved. */
    private int objective;

    /** Int for counting the total moves made during a game. */
    private int moveCount;

    /** Int for counting the total score made during a game. */
    private int score;

    /** Int 2-D array for determining the grid lengths. */
    private int[][] grid;

    /** ArrayList for storing the row, column and vale of a cell. */
    private ArrayList<Cell> cells;

    /** Variable for displaying game state such as lose, win, or in progress. */
    private GameStatus gameStatus;

    /** Random int for generating 2s and 4s. */
    private Random rand;

    /** A stack for storing previous moves. */
    private Stack<int[][]> moveRecord;

    /** A stack for storing previous scores. */
    private Stack<Integer> scoreRecord;

    /*******************************************************************
     * Default constructor that sets the 1024 game to zero.
     ******************************************************************/
    public NumberGameArrayList() {
        objective = 0;
        moveCount = 0;
        score = 0;
        grid = new int[0][0];
        cells = new ArrayList<>();
        rand = new Random();
        moveRecord = new Stack<int[][]>();
        scoreRecord = new Stack<Integer>();
    }

    /*******************************************************************
     * Returns the score of the game for display purposes.
     * @return int score - The total score of the 1024 game session.
     ******************************************************************/
    public int getScore() {
        return score;
    }

    /*******************************************************************
     * Returns the moves of the game for display purposes.
     * @return int moveCount - The total moves of the 1024 game session.
     ******************************************************************/
    public int getMoveCount() {
        return moveCount;
    }

    /***********************************************************************
     * Resets the parameters of the game to fit new grid dimensions.
     * @param NROW - Number of rows that are on the grid.
     * @param NCOL - Number of columns that are on the grid.
     * @param goal - The intended goal for the player to achieve.
     ***********************************************************************/
    @Override
    public void resizeBoard(int NROW, int NCOL, int goal) {
        int winValue = goal;

        // Checks if the winValue is divisible by 2 to equal 0.
        if (winValue % 2 == 0) {
            while (winValue != 2) {
                winValue = winValue / 2;
            }
        }

        objective = goal;
        grid = new int[NROW][NCOL];
        cells = new ArrayList<Cell>();
        gameStatus = GameStatus.IN_PROGRESS;
    }

    /***********************************************************************
     * Clears the grid by setting all nonzero numbers to 0 and randomly
     * generates a 2 or 4 in various locations.
     ***********************************************************************/
    @Override
    public void reset() {
        gameStatus = GameStatus.IN_PROGRESS;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = 0;
            }
        }

        score = 0;

        scoreRecord.removeAllElements();
        moveRecord.removeAllElements();
        placeRandomValue();
        placeRandomValue();
    }


    /***********************************************************************
     * Sets the grid to the values that are passed by the parameter.
     * @param input - A 2-D array filled with integers.
     ***********************************************************************/
    @Override
    public void setValues(final int[][] input) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++)
                grid[row][col] = input[row][col];
        }
    }

    /***********************************************************************
     * Places a 2 or 4 on the grid in a random location.
     * @return Cell c1 - A Cell objects with rows, columns and value integers.
     ***********************************************************************/
    @Override
    public Cell placeRandomValue() {
        ArrayList<Cell> numbers = new ArrayList<>();
        Cell c1;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0) {
                    numbers.add(new Cell(row, col, generateRandomValue()));
                }
            }
        }

        c1 = numbers.get(rand.nextInt(numbers.size()));
        grid[c1.row][c1.column] = c1.value;

        return c1;
    }

    /******************************************************************
     * Randomly generates a 2 or 4 that can be used in-game.
     * @return int value - The random value that is generated.
     ******************************************************************/
    public int generateRandomValue() {
        int value = rand.nextInt(10);

        if (value < 8) {
            value = 2;
        }

        else {
            value = 4;
        }

        return value;

    }

    /***********************************************************************
     * Slides the grid tiles in a specific direction controlled by player.
     * @param dir - The direction that the grid will be shifting towards.
     * @return boolean moveMade - True or false if move is made in-game.
     ***********************************************************************/
    @Override
    public boolean slide(SlideDirection dir) {
        boolean moveMade = false;
        int scoreCount = score;

        int[][] duplicateBoard;
        duplicateBoard = new int[grid.length][grid[0].length];

        // Create a copy of the current grid for the moveRecord stack.
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                duplicateBoard[row][col] = grid[row][col];
            }
        }

        // Switch used for shifting the grid in different directions.
        switch (dir) {

            // The steps for shifting the grid upwards.
            case UP:

                for (int col = 0; col < grid[0].length; col++) {
                    ArrayList<Integer> nonZeroNumbers = new ArrayList<>();

                    for (int row = 0; row < grid.length; row++) {
                        if (grid[row][col] != 0) {
                            nonZeroNumbers.add(grid[row][col]);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size() - 1; i++) {
                        if (nonZeroNumbers.get(i).equals(nonZeroNumbers.get(i + 1))) {
                            nonZeroNumbers.set(i, nonZeroNumbers.get(i) * 2);
                            nonZeroNumbers.remove(i + 1);

                            score = score + (nonZeroNumbers.get(i) * 2);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size(); i++) {
                        if (grid[i][col] != nonZeroNumbers.get(i)) {
                            grid[i][col] = nonZeroNumbers.get(i);

                            moveMade = true;
                            moveCount = moveCount + 1;
                        }
                    }

                    for (int i = nonZeroNumbers.size(); i < grid.length; i++) {
                        grid[i][col] = 0;
                    }
                }


                break;

            // The steps for shifting the grid downwards.
            case DOWN:
                for (int col = 0; col < grid[0].length; col++) {
                    ArrayList<Integer> nonZeroNumbers = new ArrayList<>();

                    for (int row = grid.length - 1; row >= 0; row--) {
                        if (grid[row][col] != 0) {
                            nonZeroNumbers.add(grid[row][col]);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size() - 1; i++) {
                        if (nonZeroNumbers.get(i).equals(nonZeroNumbers.get(i + 1))) {
                            nonZeroNumbers.set(i, nonZeroNumbers.get(i) * 2);
                            nonZeroNumbers.remove(i + 1);

                            score = score + (nonZeroNumbers.get(i) * 2);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size(); i++) {
                        if (grid[grid.length - i - 1][col] != nonZeroNumbers.get(i)) {
                            grid[grid.length - i - 1][col] = nonZeroNumbers.get(i);

                            moveMade = true;
                            moveCount = moveCount + 1;
                        }
                    }

                    for (int i = 0; i < grid.length - nonZeroNumbers.size(); i++) {
                        grid[i][col] = 0;
                    }
                }

                break;

            // The steps for shifting the grid to the left.
            case LEFT:
                for (int row = 0; row < grid.length; row++) {
                    ArrayList<Integer> nonZeroNumbers = new ArrayList<>();

                    for (int col = 0; col < grid[0].length; col++) {
                        if (grid[row][col] != 0) {
                            nonZeroNumbers.add(grid[row][col]);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size() - 1; i++) {
                        if (nonZeroNumbers.get(i).equals(nonZeroNumbers.get(i + 1))) {
                            nonZeroNumbers.set(i, nonZeroNumbers.get(i) * 2);
                            nonZeroNumbers.remove(i + 1);

                            score = score + (nonZeroNumbers.get(i) * 2);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size(); i++) {
                        if (grid[row][i] != nonZeroNumbers.get(i)) {
                            grid[row][i] = nonZeroNumbers.get(i);

                            moveMade = true;
                            moveCount = moveCount + 1;
                        }
                    }

                    for (int i = nonZeroNumbers.size(); i < grid[0].length; i++) {
                        grid[row][i] = 0;
                    }
                }

                break;

            // The steps for shifting the board to the right.
            case RIGHT:
                for (int row = 0; row < grid.length; row++) {
                    ArrayList<Integer> nonZeroNumbers = new ArrayList<>();

                    for (int col = grid[0].length - 1; col >= 0; col--) {
                        if (grid[row][col] != 0) {
                            nonZeroNumbers.add(grid[row][col]);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size() - 1; i++) {
                        if (nonZeroNumbers.get(i).equals(nonZeroNumbers.get(i + 1))) {
                            nonZeroNumbers.set(i, nonZeroNumbers.get(i) * 2);
                            nonZeroNumbers.remove(i + 1);

                            score = score + (nonZeroNumbers.get(i) * 2);
                        }
                    }

                    for (int i = 0; i < nonZeroNumbers.size(); i++) {
                        if (grid[row][grid[0].length - i - 1] != nonZeroNumbers.get(i)) {
                            grid[row][grid[0].length - i - 1] = nonZeroNumbers.get(i);

                            moveMade = true;
                            moveCount = moveCount + 1;
                        }
                    }

                    for (int i = 0; i < grid[0].length - nonZeroNumbers.size(); i++) {
                        grid[row][i] = 0;
                    }
                }

                break;

        }

        // If a moveMade is true, place a random value and store values for further use.
        if (moveMade) {
            placeRandomValue();
            moveRecord.add(duplicateBoard);
            scoreRecord.add(scoreCount);
            moveCount = moveCount + 1;
        }

        return moveMade;

    }

    /***********************************************************************
     * Generates an ArrayList filled with all nonzero numbers on the grid.
     * @return ArrayList<Cell>cells</Cell> - ArrayList of cells.
     ***********************************************************************/
    @Override
    public ArrayList<Cell> getNonEmptyTiles() {

        // checks the grid for all tiles with a nonzero number and adds them to an ArrayList<Cell>.
        ArrayList<Cell> cells = new ArrayList();
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[0].length; col++)
                if (grid[row][col] != 0) {
                    Cell c = new Cell();
                    c.row = row;
                    c.column = col;
                    c.value = grid[row][col];
                    cells.add(c);
                }

        return cells;

    }

    /*******************************************************************
     * Return the current game state.
     * @return USER_WON - When the objective has been met.
     * @return USER_LOST - When the grid fills with nonzero values.
     * @return IN_PROGRESS - When moves are still possible.
     ******************************************************************/
    @Override
    public GameStatus getStatus() {

        // Checks all tiles of the grid to see if the objective is present.
        for (Cell c : getNonEmptyTiles()) {
            if (c.value == objective) {
                return GameStatus.USER_WON;
            }
        }

        // Check if all tiles on the grid are filled with nonzero numbers.
        if (getNonEmptyTiles().size() == grid.length * grid[0].length) {
            gameStatus = GameStatus.USER_LOST;
        }

        else {
            gameStatus = GameStatus.IN_PROGRESS;
        }

        // Check if its possible for all tiles to shift horizontally.
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 1; col++) {
                if (grid[row][col] == grid[row][col + 1]) {
                    gameStatus = GameStatus.IN_PROGRESS;
                }
            }
        }

        // Check if its possible for all tiles to shift vertically.
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length - 1; row++) {
                if (grid[row][col] == grid[row + 1][col]) {
                    gameStatus = GameStatus.IN_PROGRESS;
                }
            }
        }

        return gameStatus;

    }

    /*******************************************************************
     * Retracts moves that were previously made during game.
     * @throws IllegalArgumentException if moveRecord is equal to 0.
     ******************************************************************/
    @Override
    public void undo() {
        // Checks if it is possible to use the undo button.
        if (moveRecord.size() < 1)
            throw new IllegalStateException("Undo is not possible");

        // Replaces the value at the top of the stack.
        grid = moveRecord.pop();
        score = scoreRecord.pop();
    }
}
