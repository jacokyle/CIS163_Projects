package project1;

import javax.swing.*;

/***********************************************************************
 * The ChangeMakerGUI class contains code to setup the JFrame.
 *
 * @author (Kyle Jacobson)
 * @version (09/14/18)
 ***********************************************************************/

public class ChangeMakerGUI {
    public static void main(String arg[]) {

        // Creates various elements of the JMenu.
        JMenu fileMenu;
        JMenuItem quitItem;
        JCheckBoxMenuItem quartersItem;
        JCheckBoxMenuItem dimesItem;
        JCheckBoxMenuItem nickelsItem;
        JCheckBoxMenuItem penniesItem;
        JMenuBar menus;

        // Creates various elements of the JMenu.
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit!");
        quartersItem = new JCheckBoxMenuItem("Suspend Quarters");
        dimesItem = new JCheckBoxMenuItem("Suspend Dimes");
        nickelsItem = new JCheckBoxMenuItem("Suspend Nickels");
        penniesItem = new JCheckBoxMenuItem("Suspend Pennies");

        // Adds the items to the JMenu.
        fileMenu.add(quartersItem);
        fileMenu.add(dimesItem);
        fileMenu.add(nickelsItem);
        fileMenu.add(penniesItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();

        // Adds the JMenu to the fileMenu.
        menus.add(fileMenu);

        // Creates a new JFrame with a title that exits on close.
        JFrame gui = new JFrame("Money Maker 1.0");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creates a new panel of the ChangeMakerPanelMain class.
        ChangeMakerPanelMain panel = new ChangeMakerPanelMain();

        // Returns the contentPane object of the frame.
        gui.getContentPane().add(panel);

        // Controls the visibility of the JFrame.
        gui.setSize(1000, 400);
        gui.setJMenuBar(menus);
        gui.setVisible(true);

        // Registers an ActionListener for the quitItem button.
        quitItem.addActionListener(event ->{

            // Closes the Change Maker program.
            if(quitItem == event.getSource()){
                System.exit(0);
            }
        });

        // Registers an ActionListener for the quartersItem checkbox.
        quartersItem.addActionListener(event ->{

            // Checks if the "Suspend Quarters" checkbox is selected.
            if(quartersItem.isSelected()){
                ChangeMaker.setQuartersAvail(false);
            }

            // Checks if the "Suspend Quarters" checkbox is not selected.
            else{
                ChangeMaker.setQuartersAvail(true);
            }
        });

        // Registers an ActionListener for the dimesItem checkbox.
        dimesItem.addActionListener(event ->{

            // Checks if the "Suspend Dimes" checkbox is selected.
            if(dimesItem.isSelected()){
                ChangeMaker.setDimesAvail(false);
            }

            // Checks if the "Suspend Dimes" checkbox is not selected.
            else{
                ChangeMaker.setDimesAvail(true);
            }
        });

        // Registers an ActionListener for the nickelsItem checkbox.
        nickelsItem.addActionListener(event ->{

            // Checks if the "Suspend Nickels" checkbox is selected.
            if(nickelsItem.isSelected()){
                ChangeMaker.setNickelsAvail(false);
            }

            // Checks if the "Suspend Nickels" checkbox is not selected.
            else{
                ChangeMaker.setNickelsAvail(true);
            }
        });

        // Registers an ActionListener for the penniesItem checkbox.
        penniesItem.addActionListener(event ->{

            // Checks if the "Suspend Pennies" checkbox is selected.
            if(penniesItem.isSelected()){
                ChangeMaker.setPenniesAvail(false);
            }

            // Checks if the "Suspend Pennies" checkbox is not selected.
            else{
                ChangeMaker.setPenniesAvail(true);
            }
        });
    }
}