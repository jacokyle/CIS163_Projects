package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/***********************************************************************
 * The DialogRentGame class displays a JDialog box when the customer
 * attempts to rent a video game.
 *
 * @author (Kyle Jacobson)
 * @version (10/18/18)
 ***********************************************************************/
public class DialogRentGame extends JDialog implements ActionListener {

    /** JTextField for representing the title text. */
    private JTextField titleTxt;

    /** JTextField for representing the name of renter text. */
    private JTextField renterTxt;

    /** JTextField for representing the rental date text. */
    private JTextField rentedOnTxt;

    /** JTextField for representing the return date text. */
    private JTextField dueBackTxt;

    /** JButton for representing the ok button. */
    private JButton okButton;

    /** JButton for representing the cancel button. */
    private JButton cancelButton;

    /** Boolean for representing the JDialog box state. */
    private boolean closeStatus;

    /** Game object for creating a new object of DVD. */
    private Game unit;

    /** JComboBox of type PlayerType for deciding video game platform. */
    private JComboBox<PlayerType> combo;

    /******************************************************************
     * Instantiate a Custom Dialog as 'modal' and wait for the
     * user to provide data and click on a button.
     * @param parent - Reference to the JFrame application.
     * @param d - An instantiated object to be filled with data.
     ******************************************************************/
    public DialogRentGame(JFrame parent, DVD d) {
        // Calls the parent and creates a 'modal' dialog.
        super(parent, true);

        setTitle("Rent a Game:");
        closeStatus = false;
        setSize(400,200);

        // Creates a new object for the DVD.
        unit = (Game) d;

        // Prevents the user from closing window.
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Instantiates and displays text fields.
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(6,2));

        // Creates a JLabel and JTextField for the customer.
        textPanel.add(new JLabel("Your Name:"));
        renterTxt = new JTextField("John Doe",30);
        textPanel.add(renterTxt);

        // Creates a JLabel and JTextField for the video game.
        textPanel.add(new JLabel("Title of Game:"));
        titleTxt = new JTextField("Halo",30);
        textPanel.add(titleTxt);

        // Creates and formats the dates.
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        // Creates a JLabel and JTextField for the date of rental.
        textPanel.add(new JLabel("Rented on Date: "));
        rentedOnTxt = new JTextField(df.format(date),30);
        textPanel.add(rentedOnTxt);

        // Adds a day to the current date to be used for return date.
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);  // Number of days to add.
        date = c.getTime();

        // Creates a JLabel and JTextField for the date of return.
        textPanel.add(new JLabel("Due Back: "));
        dueBackTxt = new JTextField(df.format(date),15);
        textPanel.add(dueBackTxt);

        // Creates a JLabel for the video game platform.
        textPanel.add(new JLabel("Type of Player: "));

        // Creates a JComboBox for the video game platform.
        combo = new JComboBox<PlayerType>(PlayerType.values());
        textPanel.add(combo);

        // Adds the textPanel to the content pane.
        getContentPane().add(textPanel, BorderLayout.CENTER);

        // Instantiate and display two buttons.
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Sets the dimensions and visibility of the content pane.
        setSize(300,300);
        setVisible(true);
    }

    /******************************************************************
     * Responds appropriately when the button is clicked.
     * @param e - The action event that was just fired.
     ******************************************************************/
    public void actionPerformed(ActionEvent e) {

        // Creates a new JButton for the action performed.
        JButton button = (JButton) e.getSource();

        // If OK is clicked, fill the table with information.
        if (button == okButton) {
            // Save the information in the object.
            closeStatus = true;

            // Formats the date.
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

            // Creates objects of Date.
            Date dateRentedOn, dateDue;

            // Interpretation of the inputted date will not be lenient.
            df.setLenient(false);

            // Tries to get text and parses it, then displays information.
            try {
                dateRentedOn = df.parse(rentedOnTxt.getText());
                dateDue = df.parse(dueBackTxt.getText());

                GregorianCalendar rentedOn = new GregorianCalendar();
                GregorianCalendar dueBackOn = new GregorianCalendar();

                rentedOn.setTime(dateRentedOn);
                dueBackOn.setTime(dateDue);

                unit.setRentedOn(rentedOn);
                unit.setDueBack(dueBackOn);
                unit.setNameOfRenter(renterTxt.getText());
                unit.setTitle(titleTxt.getText());
                unit.setPlayer((PlayerType) combo.getSelectedItem());

                if(unit.rentedOn.after(unit.dueBack)) {
                    JOptionPane.showMessageDialog(null, "The Rental Date Cannot Be After the Due Date");
                    dispose();
                    return;
                }
            }

            // Catches an error when there is an Exception while trying.
            catch (Exception e1) {
                System.out.println ("I have unexpectedly quit, sorry! Goodbye!");
            }
        }

        // Forces the JDialog box to disappear.
        dispose();
    }

    /*******************************************************************
     * Return a String to let the caller know which button
     * was clicked.
     * @return int representing the option OK or CANCEL.
     ******************************************************************/
    public boolean closeOK() {
        return closeStatus;
    }
}




