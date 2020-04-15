package project2;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/***********************************************************************
 * The GUI1024Panel sets up JFrame panel and key configuration.
 *
 * @author (Kyle Jacobson)
 * @version (09/25/28)
 ***********************************************************************/

public class GUI1024Panel extends JPanel {

    private final static int DEFAULT_SIZE = 4;
    private JLabel[][] gameBoardUI;
    private NumberGameArrayList gameLogic;

    public GUI1024Panel() {
        setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        setLayout(new GridLayout(DEFAULT_SIZE, DEFAULT_SIZE));

        gameBoardUI = new JLabel[DEFAULT_SIZE][DEFAULT_SIZE];

        Font myTextFont = new Font(Font.SERIF, Font.BOLD, 40);
        for (int row = 0; row < gameBoardUI.length; row++) {
            for (int col = 0; col < gameBoardUI[row].length; col++) {
                gameBoardUI[row][col] = new JLabel();
                gameBoardUI[row][col].setFont(myTextFont);
                gameBoardUI[row][col].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                gameBoardUI[row][col].setPreferredSize(new Dimension(100, 100));
                gameBoardUI[row][col].setHorizontalAlignment(JLabel.CENTER);
                add(gameBoardUI[row][col]);
            }
        }

        gameLogic = new NumberGameArrayList();
        gameLogic.resizeBoard(DEFAULT_SIZE, DEFAULT_SIZE, 16);
        gameLogic.placeRandomValue();

        updateBoard();
        setFocusable(true);
        addKeyListener(new SlideListener());
    }

    private void updateBoard() {
        for (JLabel[] row : gameBoardUI)
            for (JLabel s : row) {
                s.setText("");
            }

        ArrayList<Cell> out = gameLogic.getNonEmptyTiles();
        if (out == null) {
            JOptionPane.showMessageDialog(null, "Incomplete implementation getNonEmptyTiles()");
            return;
        }
        for (Cell c : out) {
            JLabel z = gameBoardUI[c.row][c.column];
            z.setText(String.valueOf(Math.abs(c.value)));
            z.setForeground(c.value > 0 ? Color.BLACK : Color.RED);
        }
    }

    private class SlideListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            boolean moved = false;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    moved = gameLogic.slide(SlideDirection.UP);
                    break;
                case KeyEvent.VK_LEFT:
                    moved = gameLogic.slide(SlideDirection.LEFT);
                    break;
                case KeyEvent.VK_DOWN:
                    moved = gameLogic.slide(SlideDirection.DOWN);
                    break;
                case KeyEvent.VK_RIGHT:
                    moved = gameLogic.slide(SlideDirection.RIGHT);
                    break;
            }
            if (moved) {
                updateBoard();
                if (gameLogic.getStatus().equals(GameStatus.USER_WON))
                    JOptionPane.showMessageDialog(null, "You won!");
                else if (gameLogic.getStatus().equals(GameStatus.USER_LOST)) {
                    int resp = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Game Over!",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resp == JOptionPane.YES_OPTION) {
                        gameLogic.reset();
                        updateBoard();
                    } else {
                        System.exit(0);
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
