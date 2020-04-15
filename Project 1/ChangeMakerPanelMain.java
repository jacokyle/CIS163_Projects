package project1;

import javax.swing.*;
import java.awt.*;

/***********************************************************************
 * The ChangeMakerPanelMain class creates three panels for the GUI.
 *
 * @author (Kyle Jacobson)
 * @version (09/14/18)
 ***********************************************************************/

class ChangeMakerPanelMain extends JPanel {

    ChangeMakerPanelMain() {

        // Creates three separate panels for the Change Maker.
        setLayout(new GridLayout(1, 3));

        // Creates the first panel.
        ChangeMakerPanel p1 = new ChangeMakerPanel();

        // Creates the second panel.
        ChangeMakerPanel p2 = new ChangeMakerPanel();

        // Creates the third panel.
        ChangeMakerPanel p3 = new ChangeMakerPanel();

        // Adds the first panel to the JFrame.
        add(p1);

        // Adds the second panel to the JFrame.
        add(p2);

        // Adds the third panel to the JFrame.
        add(p3);
    }
}