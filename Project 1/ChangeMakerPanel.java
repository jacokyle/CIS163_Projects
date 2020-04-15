package project1;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

/***********************************************************************
 * The ChangeMakerPanel class creates and organizes the elements of the GUI.
 *
 * @author (Kyle Jacobson)
 * @version (09/14/18)
 ***********************************************************************/

class ChangeMakerPanel extends JPanel {

    /** Object for the ChangeMaker. */
    private ChangeMaker ATM;

    /** JLabels for displaying the title, currency and coin values. */
    private JLabel title, amount, amountValue, transferAmount, quarters, quarterValue, dimes, dimeValue, nickels, nickelValue, pennies, pennyValue, error;

    /** JButtons for activating the ChangeMaker methods. */
    private JButton loadBtn, takeOutBtn, loadFromFileBtn, saveToFileBtn;

    /** JTextField for the money being withdrawn. */
    private JTextField currency;

    /** Double that is created from parsing in the JTextField. */
    private double cash;

    /** Decimal Formatter that is used to display money appropriately. */
    DecimalFormat df = new DecimalFormat("0.00");

    /*******************************************************************
     * Constructor: Sets up the main GUI components.
     *******************************************************************/
    ChangeMakerPanel() {

        // Starts the initial ChangeMaker with 10 dollars.
        ATM = new ChangeMaker(10);

        // Helps structure the overall layout of the JComponents.
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();
        position.insets = new Insets(10, 10, 10, 10);
        position.fill = GridBagConstraints.HORIZONTAL;

        // Creates and places the title of the ChangeMaker in the correct location.
        title = new JLabel("Change Maker 1.0");
        Font myFont = new Font("verdana", Font.BOLD, 18);
        title.setFont(myFont);
        add(title, position);

        // Creates and places the dynamic error messages in the correct location.
        error = new JLabel();
        error.setForeground(Color.RED);
        position.gridx = 10;
        position.gridy = 0;
        add(error, position);

        // Creates and places the amount JLabel in the correct location.
        amount = new JLabel("Amount:");
        position.gridx = 0;
        position.gridy = 10;
        add(amount, position);

        // Creates and places the amount value in the correct location.
        amountValue = new JLabel();
        amountValue.setText("$" + df.format(ATM.getAmount()));
        position.gridx = 10;
        position.gridy = 10;
        add(amountValue, position);

        // Creates and places the transfer amount JLabel in the correct location.
        transferAmount = new JLabel("Transfer Amount:");
        position.gridx = 0;
        position.gridy = 20;
        add(transferAmount, position);

        // Creates and places the takeOut JTextField in the correct location.
        currency = new JTextField(15);
        position.gridx = 10;
        position.gridy = 20;
        add(currency, position);

        // Creates and places the "Load" JButton in the correct location.
        loadBtn = new JButton("Load");
        position.gridx = 0;
        position.gridy = 30;
        add(loadBtn, position);

        // Creates and places the "Take Out!" JButton in the correct location.
        takeOutBtn = new JButton("Take Out!");
        position.gridx = 10;
        position.gridy = 30;
        add(takeOutBtn, position);

        // Creates and places the quarters JLabel in the correct location.
        quarters = new JLabel("Quarters:");
        position.gridx = 0;
        position.gridy = 40;
        add(quarters, position);

        // Creates and places the quarters value in the correct location.
        quarterValue = new JLabel();
        quarterValue.setText("" + 0);
        position.gridx = 10;
        position.gridy = 40;
        add(quarterValue, position);

        // Creates and places the dimes JLabel in the correct location.
        dimes = new JLabel("Dimes:");
        position.gridx = 0;
        position.gridy = 50;
        add(dimes, position);

        // Creates and places the dimes value in the correct location.
        dimeValue = new JLabel();
        dimeValue.setText("" + 0);
        position.gridx = 10;
        position.gridy = 50;
        add(dimeValue, position);

        // Creates and places the nickels JLabel in the correct location.
        nickels = new JLabel("Nickels:");
        position.gridx = 0;
        position.gridy = 60;
        add(nickels, position);

        // Creates and places the nickels value in the correct location.
        nickelValue = new JLabel();
        nickelValue.setText("" + 0);
        position.gridx = 10;
        position.gridy = 60;
        add(nickelValue, position);

        // Creates and places the pennies JLabel in the correct location.
        pennies = new JLabel("Pennies:");
        position.gridx = 0;
        position.gridy = 70;
        add(pennies, position);

        // Creates and places the pennies value in the correct location.
        pennyValue = new JLabel();
        pennyValue.setText("" + 0);
        position.gridx = 10;
        position.gridy = 70;
        add(pennyValue, position);

        // Creates and places the "Load from File" JButton in the correct location.
        loadFromFileBtn = new JButton("Load from File");
        position.gridx = 0;
        position.gridy = 80;
        add(loadFromFileBtn, position);

        // Creates and places the "Save to File" JButton in the correct location.
        saveToFileBtn = new JButton("Save to File");
        position.gridx = 10;
        position.gridy = 80;
        add(saveToFileBtn, position);

        // Creates a new ButtonListener object used for the ActionListeners.
        ButtonListener butListener = new ButtonListener();

        // Registers the listeners for the buttons.
        loadBtn.addActionListener(butListener);
        takeOutBtn.addActionListener(butListener);
        loadFromFileBtn.addActionListener(butListener);
        saveToFileBtn.addActionListener(butListener);
    }

    private void openFile(){
        // Creates a File Chooser so that it starts at the current directory.
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // Opens a File Chooser and waits for user selection.
        int returnVal = fc.showOpenDialog(this);

        // Check if the user selects a file.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();

            // Change the amount value with the selected file.
            amountValue.setText(filename);
        }
    }

    /*******************************************************************
     * Represents an action listener for the ChangeMaker buttons.
     *******************************************************************/
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Loads the machine with 10 dollars when clicked.
            if (loadBtn == event.getSource()) {

                // Increments the amount by 10 dollars.
                ATM.loadMachine(10);

                // Hides the error message when money is added.
                error.setText("");
            }

            // Takes out the inputted amount of money in the JTextField.
            if (takeOutBtn == event.getSource()) {

                // Converts the input of the JTextField into a double.
                cash = Double.parseDouble(currency.getText());

                // Issues an error message when inputted value is equal to or less than 0.
                if (cash <= 0)
                    error.setText("Error: Invalid Amount");

                // Issues an error message when the inputted value has more than 2 decimals.
                if (Math.floor(cash * 100) / 100 != (cash * 100) / 100)
                    error.setText("Error: Invalid Amount");

                // Issues an error message when the inputted value is equal to or greater than 1.0E15
                if (cash >= 1.0E15)
                    error.setText("Error: Invalid Amount");

                // Takes the double created from parsing and subtracts it from amount.
                ATM.takeOut(cash);

                // Checks if the total amount is less than 0.
                if(ATM.getAmount() < 0){

                    // Amount is now set to 0 for display purposes.
                    ATM.setAmount(0);

                    // Issues an error message notifying that the machine is empty.
                    error.setText("Error: No Money Available");

                    // Acknowledges machine is empty and sets quarters to 0.
                    if(ChangeMaker.isQuartersAvail()){

                        // Amount of quarters is now set to 0 for display purposes.
                        quarterValue.setText("" + 0);
                    }

                    // Acknowledges machine is empty and sets dimes to 0.
                    if(ChangeMaker.isDimesAvail()){

                        // Amount of dimes is now set to 0 for display purposes.
                        dimeValue.setText("" + 0);
                    }

                    // Acknowledges machine is empty and sets nickels to 0.
                    if(ChangeMaker.isNickelsAvail()){

                        // Amount of nickels is now set to 0 for display purposes.
                        nickelValue.setText("" + 0);
                    }

                    // Acknowledges machine is empty and sets pennies to 0.
                    if(ChangeMaker.isPenniesAvail()){

                        // Amount of pennies is now set to 0 for display purposes.
                        pennyValue.setText("" + 0);
                    }
                }
            }

            // Opens up a file browser to load up a specific amount of money.
            if (loadFromFileBtn == event.getSource()) {
                // Opens a File Chooser.
                openFile();

                // Loads the file using the appropriate String name.
                ATM.load("file.txt");
            }

            // Saves a file of the amount that can be loaded through the file browser.
            if (saveToFileBtn == event.getSource()) {

                // Saves the file using the appropriate String name.
                ATM.save("file.txt");
            }

            // Updates the amount value whenever the money is taken out of the machine.
            amountValue.setText("$" + df.format(ATM.getAmount()));

            // Checks if the total amount is more than 0.
            if(ATM.getAmount() > 0){

                // Calculates the amount of quarters that are given by the inputted value.
                if(ChangeMaker.isQuartersAvail()){

                    //Calculates the amount of quarters using math.
                    double quarters = (cash / .25);

                    // Displays the amount of quarters calculated on a JLabel.
                    quarterValue.setText("" + (int)quarters);
                }

                // Returns 0 quarters if they are suspended.
                else{

                    // Displays the amount of quarters when they are not selected.
                    quarterValue.setText("" + 0);
                }

                // Calculates the amount of dimes that are given by the inputted value.
                if(ChangeMaker.isDimesAvail()){

                    //Calculates the amount of quarters using math.
                    double dimes = (cash / .10);

                    // Displays the amount of dimes calculated on a JLabel.
                    dimeValue.setText("" + (int)dimes);
                }

                // Returns 0 dimes if they are suspended.
                else{

                    // Displays the amount of quarters when they are not selected.
                    dimeValue.setText("" + 0);
                }

                // Calculates the amount of nickels that are given by the inputted value.
                if(ChangeMaker.isNickelsAvail()){

                    //Calculates the amount of nickels using math.
                    double nickels = (cash / .05);

                    // Displays the amount of nickels calculated on a JLabel.
                    nickelValue.setText("" + (int)nickels);
                }

                // Returns 0 nickels if they are suspended.
                else{

                    // Displays the amount of nickels when they are not selected.
                    nickelValue.setText("" + 0);
                }

                // Calculates the amount of pennies that are given by the inputted value.
                if(ChangeMaker.isPenniesAvail()){

                    //Calculates the amount of pennies using math.
                    double pennies = (cash / .01);

                    // Displays the amount of pennies calculated on a JLabel.
                    pennyValue.setText("" + (int)pennies);
                }

                // Returns 0 pennies if they are suspended.
                else{
                    pennyValue.setText("" + 0);
                }
            }
        }
    }
}