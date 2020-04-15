package project2;

import javax.swing.*;
import java.awt.*;

/***********************************************************************
 * The GUI1024 class adds usable buttons and runs the 1024 GUI.
 *
 * @author (Kyle Jacobson)
 * @version (09/25/28)
 ***********************************************************************/
public class GUI1024 extends JPanel {
    public static void main(String arg[]) {

        // Creates an object of NumberGameArrayList called gameLogic.
        NumberGameArrayList gameLogic = new NumberGameArrayList();

        // Helps organize the JButtons that are present on the JFrame.
        GridBagConstraints position = new GridBagConstraints();

        // Creates various elements of the JMenu.
        JMenu fileMenu = new JMenu("File");
        JMenuItem scoreItem = new JMenuItem("Change Score");
        JMenuItem sizeItem = new JMenuItem("Change Size");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem quitItem = new JMenuItem("Quit");
        JMenuBar menus;

        // Creates several JButtons for shifting the grid.
        JButton upBtn;
        JButton downBtn;
        JButton leftBtn;
        JButton rightBtn;
        JButton undoBtn;

        // Creates several JLabels used for tracking statistics.
        JLabel scoreValue;
        JLabel moveValue;

        // Adds the items to the JMenu.
        fileMenu.add(scoreItem);
        fileMenu.add(sizeItem);
        fileMenu.add(resetItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();

        // Adds the JMenu to the fileMenu.
        menus.add(fileMenu);

        // Creates a new JFrame with a title that exits on close.
        JFrame gui = new JFrame("Welcome to 1024!");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates a new panel for the game statistics.
        GUI1024 statistics = new GUI1024();
        gui.getContentPane().add(statistics, BorderLayout.NORTH);

        // Creates a new panel for the grid.
        GUI1024Panel panel = new GUI1024Panel();
        gui.getContentPane().add(panel, BorderLayout.CENTER);

        // Creates a new panel for the slide buttons.
        GUI1024 shiftButtons = new GUI1024();
        gui.getContentPane().add(shiftButtons, BorderLayout.SOUTH);

        // Controls the visibility of the JFrame.
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gui.setJMenuBar(menus);
        gui.setVisible(true);

        // Adds the shift left button..
        leftBtn = new JButton("Shift Left");
        leftBtn.setPreferredSize(new Dimension(300, 50));
        shiftButtons.add(leftBtn, position);

        // Adds the shift up button.
        upBtn = new JButton("Shift Up");
        upBtn.setPreferredSize(new Dimension(300, 50));
        shiftButtons.add(upBtn, position);

        // Adds the shift right button.
        rightBtn = new JButton("Shift Right");
        rightBtn.setPreferredSize(new Dimension(300, 50));
        shiftButtons.add(rightBtn, position);

        // Adds the shift down button.
        downBtn = new JButton("Shift Down");
        downBtn.setPreferredSize(new Dimension(300, 50));
        shiftButtons.add(downBtn, position);

        // Adds the undo button.
        undoBtn = new JButton("Undo Move");
        undoBtn.setPreferredSize(new Dimension(300, 50));
        shiftButtons.add(undoBtn, position);

        // Creates and places the score value in the correct location.
        scoreValue = new JLabel();
        scoreValue.setText("Score: " + gameLogic.getScore());
        statistics.add(scoreValue, position);

        // Creates and places the total moves value in the correct location.
        moveValue = new JLabel();
        moveValue.setText("Total Moves: " + gameLogic.getMoveCount());
        statistics.add(moveValue, position);

        // Registers an ActionListener for the scoreItem button.
        scoreItem.addActionListener(event -> {

            // Alters the score of the game board for the 1024 program.
            if (scoreItem == event.getSource()) {

            }
        });

        // Registers an ActionListener for the sizeItem button.
        sizeItem.addActionListener(event -> {

            // Alters the size of the game board for the 1024 program.
            if (sizeItem == event.getSource()) {
                gameLogic.resizeBoard(6,6,1024);
            }
        });

        // Registers an ActionListener for the resetItem button.
        resetItem.addActionListener(event -> {

            // Resets the 1024 program.
            if (resetItem == event.getSource()) {
                gameLogic.reset();
            }
        });

        // Registers an ActionListener for the upBtn button.
        upBtn.addActionListener(event -> {

            // Shifts the grid to the up.
            if (upBtn == event.getSource()) {
                gameLogic.slide(SlideDirection.UP);

                moveValue.setText("" + gameLogic.getMoveCount());
                scoreValue.setText("" + gameLogic.getScore());
            }
        });

        // Registers an ActionListener for the downBtn button.
        downBtn.addActionListener(event -> {

            // Shifts the grid to the down.
            if (downBtn == event.getSource()) {
                gameLogic.slide(SlideDirection.DOWN);

                moveValue.setText("" + gameLogic.getMoveCount());
                scoreValue.setText("" + gameLogic.getScore());
            }
        });

        // Registers an ActionListener for the leftBtn button.
        leftBtn.addActionListener(event -> {

            // Shifts the grid to the left.
            if (leftBtn == event.getSource()) {
                gameLogic.slide(SlideDirection.LEFT);

                moveValue.setText("" + gameLogic.getMoveCount());
                scoreValue.setText("" + gameLogic.getScore());
            }
        });

        // Registers an ActionListener for the rightBtn button.
        rightBtn.addActionListener(event -> {

            // Shifts the grid to the right.
            if (rightBtn == event.getSource()) {
                gameLogic.slide(SlideDirection.RIGHT);

                moveValue.setText("" + gameLogic.getMoveCount());
                scoreValue.setText("" + gameLogic.getScore());
            }
        });

        // Registers an ActionListener for the undoBtn button.
        undoBtn.addActionListener(event -> {

            // Rewinds the most recent move made by the player.
            if (undoBtn == event.getSource()) {
                gameLogic.undo();
            }
        });

        // Registers an ActionListener for the quitItem button.
        quitItem.addActionListener(event -> {

            // Closes the 1024 program.
            if (quitItem == event.getSource()) {
                System.exit(0);
            }
        });
    }
}

