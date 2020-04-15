package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/***********************************************************************
 * The DialogDaysLate class displays a JDialog box when the customer
 * returns an BluRay or Game later than the due date.
 *
 * @author (Kyle Jacobson)
 * @version (10/18/18)
 ***********************************************************************/
public class DialogDaysLate extends JDialog implements ActionListener {

	/** JTextField for representing the dueBack text. */
	private JTextField dueBackTxt;

    /** JButton for representing the ok button. */
    private JButton okButton;

    /** JButton for representing the cancel button. */
    private JButton cancelButton;

    /** Boolean for representing the JDialog box state. */
    private boolean closeStatus;

    /** GregorianCalendar object for representing inputted date. */
    private GregorianCalendar dayOfTheYear;

	/******************************************************************
	 * Instantiate a Custom Dialog as 'modal' and wait for the
	 * user to provide data and click on a button.
	 * @param parent - Reference to the JFrame application.
	 ******************************************************************/
	public DialogDaysLate(JFrame parent, GregorianCalendar dayOfTheYear, String title) {
		// Calls the parent and creates a 'modal' dialog.
		super(parent, true);

		// Sets the title and specifications of the JFrame.
		setTitle(title);
		closeStatus = false;
		setSize(400, 200);

		// Creates a new object for the GregorianCalender.
		this.dayOfTheYear = dayOfTheYear;

		// Prevents the user from closing window.
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// Instantiates and displays text fields.
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(6, 2));

		// Creates and formats the dates.
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        // Adds a day to the current date to be used for return date.
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);  // Number of days to add.
		date = c.getTime();

		// Creates a JLabel and JTextField for the date of return.
		textPanel.add(new JLabel("Date: "));
		dueBackTxt = new JTextField(df.format(date), 15);
		textPanel.add(dueBackTxt);

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
		setSize(300, 300);
		setVisible(true);
	}

    /***********************************************************************
     * Returns the GregorianCalender value for dayOfTheYear.
     * @return GregorianCalendar dayOfTheYear
     ***********************************************************************/
	public GregorianCalendar getDayOfTheYear() {
		return dayOfTheYear;
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
			Date dateDue;

			// Interpretation of the inputted date will not be lenient.
            df.setLenient(false);

            // Tries to get text and parses it, then displays information.
			try {
				dateDue = df.parse(dueBackTxt.getText());
				dayOfTheYear.setTime(dateDue);
			}

            // Catches an error when there is an Exception while trying.
            catch (ParseException e1) {
				System.out.println("I have unexpectedly quit, sorry! Goodbye!");
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