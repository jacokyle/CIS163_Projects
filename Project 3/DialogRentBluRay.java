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
 * The DialogRentBluRay class displays a JDialog box when the customer
 * attempts to rent a BluRay disc.
 *
 * @author (Kyle Jacobson)
 * @version (10/18/18)
 ***********************************************************************/
public class DialogRentBluRay extends JDialog implements ActionListener {

	/** JTextField for representing the title text. */
	private JTextField titleTxt;

	/** JTextField for representing the name of renter text. */
	private JTextField renterTxt;

	/** JTextField for representing the rental date text. */
	private JTextField rentedOnTxt;

	/** JTextField for representing the return date text. */
	private JTextField DueBackTxt;

	/** JButton for representing the ok button. */
	private JButton okButton;

	/** JButton for representing the cancel button. */
	private JButton cancelButton;

	/** Boolean for representing the JDialog box state. */
	private boolean closeStatus;

	/** BluRay object for creating a new object of DVD. */
	private BluRay unit;

	/** JComboBox of type Boolean for deciding BluRay layering. */
	private JComboBox<Boolean> combo;

	/******************************************************************
	 * Instantiate a Custom Dialog as 'modal' and wait for the
	 * user to provide data and click on a button.
     * @param parent - Reference to the JFrame application.
     * @param d - An instantiated object to be filled with data.
	 ******************************************************************/
	public DialogRentBluRay(JFrame parent, DVD d) {
		// Calls the parent and creates a 'modal' dialog.
		super(parent, true);

		// Sets the title and specifications of the JFrame.
		setTitle("Rent a BluRay:");
		closeStatus = false;
		setSize(400,200);

		// Creates a new object for the DVD.
		unit = (BluRay) d;

		// Prevents the user from closing window.
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		// Instantiates and displays text fields.
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(6,2));

		// Creates a JLabel and JTextField for the customer.
		textPanel.add(new JLabel("Your Name:"));
		renterTxt = new JTextField("John Doe",30);
		textPanel.add(renterTxt);

		// Creates a JLabel and JTextField for the BluRay.
		textPanel.add(new JLabel("Title of BluRay:"));
		titleTxt = new JTextField("Avengers",30);
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
		DueBackTxt = new JTextField(df.format(date),15);
		textPanel.add(DueBackTxt);

		// Creates a JLabel for the BluRay layer type.
		textPanel.add(new JLabel("Double Layer: "));

		// Creates a JComboBox for the BluRay layer type.
		combo = new JComboBox<Boolean>();
		combo.addItem(Boolean.TRUE);
		combo.addItem(Boolean.FALSE);
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
				dateDue = df.parse(DueBackTxt.getText());
				
				GregorianCalendar rentedOn = new GregorianCalendar();
				GregorianCalendar dueBackOn = new GregorianCalendar();

				rentedOn.setTime(dateRentedOn);
				dueBackOn.setTime(dateDue);
				
				unit.setRentedOn(rentedOn);
				unit.setDueBack(dueBackOn);
				unit.setNameOfRenter(renterTxt.getText());
				unit.setTitle(titleTxt.getText());
				unit.setDoubleLayer((Boolean) combo.getSelectedItem());

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
	public boolean closeOK(){
		return closeStatus;
	}
	
}



