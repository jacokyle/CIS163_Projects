package project2;

import java.util.Scanner;

/***********************************************************************
 * The TextUI class is for playing/testing the 1024 game using text.
 *
 * @author (Kyle Jacobson)
 * @version (09/25/28)
 ***********************************************************************/
public class TextUI {

    /** object of NumberSlider called game. */
    private NumberSlider game;

    /** int 2-D array for determining the grid lengths. */
    private int[][] grid;

    /** int for setting the width of the cells. */
    private static int CELL_WIDTH = 3;

    /** strings for printing out the formatted grid. */
    private static String NUM_FORMAT, BLANK_FORMAT;

    /** object of Scanner for reading moves made by player. */
    private Scanner inp;

    /*******************************************************************
     * Default constructor that initializes the textUI version of 1024.
     ******************************************************************/
    public TextUI() {
        game = new NumberGameArrayList();

        if (game == null) {
            System.err.println ("*---------------------------------------------*");
            System.err.println ("| You must first modify the UI program.       |");
            System.err.println ("| Look for the first TODO item in TextUI.java |");
            System.err.println ("*---------------------------------------------*");
            System.exit(0xE0);
        }
        game.resizeBoard(4, 4, 64);
        grid = new int[4][4];

        // Set the string to %4d.
        NUM_FORMAT = String.format("%%%dd", CELL_WIDTH + 1);

        // Set the string to %4s, but without using String.format().
        BLANK_FORMAT = "%" + (CELL_WIDTH + 1) + "s";
        inp = new Scanner(System.in);
    }

    /*******************************************************************
     * Resets and generates a grid that can be seen using text.
     ******************************************************************/
    public void renderBoard() {
        // Reset all the 2D array elements to zero.
        for (int k = 0; k < grid.length; k++) {
            for (int m = 0; m < grid[k].length; m++) {
                grid[k][m] = 0;
            }
        }

        // Fill in the 2D array using information from non-empty tiles.
        for (Cell c : game.getNonEmptyTiles()) {
            grid[c.row][c.column] = c.value;
        }

        // Print the 2D array using dots and numbers.
        for (int k = 0; k < grid.length; k++) {
            for (int m = 0; m < grid[k].length; m++) {
                if (grid[k][m] == 0)
                    System.out.printf(BLANK_FORMAT, ".");
                else
                    System.out.printf(NUM_FORMAT, grid[k][m]);
            }
            System.out.println();
        }
    }

    /*******************************************************************
     * The main loop for playing a SINGLE game session. Notice that
     * the following method contains NO GAME LOGIC! Its main task is
     * to accept user input and invoke the appropriate methods in the
     * game engine.
     ******************************************************************/
    public void playLoop() {
        char keyboardSelection;

        // Place the first two random tiles.
        game.placeRandomValue();
        game.placeRandomValue();
        renderBoard();

        // To keep the right margin within 75 columns, we split the
        // following long string literal into two lines.
        System.out.print ("Slide direction (W, A, S, D), " +
                "[U]ndo or [Q]uit? ");

        // Continue to make moves until the game is closed.
        do{

            keyboardSelection = inp.next().toUpperCase().trim().charAt(0);

            // Inputs that will shift the nonzero numbers on board.
            switch (keyboardSelection) {
                case 'W':
                    game.slide(SlideDirection.UP);
                    break;
                case 'A':
                    game.slide(SlideDirection.LEFT);
                    break;
                case 'S':
                    game.slide(SlideDirection.DOWN);
                    break;
                case 'D':
                    game.slide(SlideDirection.RIGHT);
                    break;
                case 'U':
                    try {
                        game.undo();
                    } catch (IllegalStateException error) {
                        System.out.println ("Undo is not possible.");
                    }
                    break;
                case 'Q':
                    System.exit(0);
                    break;
            }

            renderBoard();

        }while (game.getStatus() == GameStatus.IN_PROGRESS);

        // Allows players to be notified of their performance.
        switch (game.getStatus()) {
            case IN_PROGRESS:
                System.out.println ("Thanks for playing!");
                break;
            case USER_WON:
                System.out.println ("Congratulations, you win!");
                break;
            case USER_LOST:
                System.out.println ("Sorry, you lose!");
                break;
        }
    }

    /*******************************************************************
     * Main method that is utilized for running the TextUI class.
     ******************************************************************/
    public static void main(String[] arg) {
        TextUI t = new TextUI();
        t.playLoop();
    }
}



