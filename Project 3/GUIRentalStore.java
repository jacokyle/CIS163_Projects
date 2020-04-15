package project3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/***********************************************************************
 * The GUIRentalStore class displays the programs components.
 *
 * @author (Kyle Jacobson)
 * @version (10/18/18)
 ***********************************************************************/
public class GUIRentalStore extends JFrame implements ActionListener{

	/** Holds menu bar. */
	private JMenuBar menus;

	/** Menus that are present in the menu bar. */
	private JMenu fileMenu;
	private JMenu actionMenu;
	private JMenu helpMenu;

	/** Menu items in each of the menus. */
	private JMenuItem openSerItem;
	private JMenuItem exitItem;
	private JMenuItem saveSerItem;
	private JMenuItem openTextItem;
	private JMenuItem saveTextItem;
	private JMenuItem rentBluRay;
	private JMenuItem rentGame;
	private JMenuItem returnItem;
	private JMenuItem dayLate;
	private JMenuItem undo;
	private JCheckBoxMenuItem filterTog;

	/** Holds the list engine. */
	private TableDVDs tableDVDs;

	/** Holds the jTable. */
	private JTable jTable;

	/*******************************************************************
	 * A constructor that starts a new GUI for the rental store.
	 ******************************************************************/
	public GUIRentalStore(){
		//Adds a menu bar and menu items.
		menus = new JMenuBar();
		fileMenu = new JMenu("File");
		actionMenu = new JMenu("Action");
		helpMenu = new JMenu("Help");
		openSerItem = new JMenuItem("Open File");
		exitItem = new JMenuItem("Exit");
		saveSerItem = new JMenuItem("Save File");
		openTextItem = new JMenuItem("Open Text");
		saveTextItem = new JMenuItem("Save Text");
		rentBluRay = new JMenuItem("Rent BluRay");
		rentGame = new JMenuItem("Rent Game");
		returnItem = new JMenuItem("Return");
		dayLate = new JMenuItem("Days Late");
		filterTog = new JCheckBoxMenuItem("Filter Toggle");
		undo = new JMenuItem("Undo");

		// Adds items that are within the menus.
		fileMenu.add(openSerItem);
		fileMenu.add(saveSerItem);
		fileMenu.addSeparator();
		fileMenu.add(openTextItem);
		fileMenu.add(saveTextItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		actionMenu.add(rentBluRay);
		actionMenu.add(rentGame);
		actionMenu.addSeparator();
		actionMenu.add(returnItem);
		actionMenu.addSeparator();
		actionMenu.add(dayLate);
		actionMenu.add(filterTog);
		helpMenu.add(undo);

		// Adds menus that are on the bar.
		menus.add(fileMenu);
		menus.add(actionMenu);
		menus.add(helpMenu);

		// Adds actionListener for each of the items.
		openSerItem.addActionListener(this);
		saveSerItem.addActionListener(this);
		openTextItem.addActionListener(this);
		saveTextItem.addActionListener(this);
		exitItem.addActionListener(this);
		rentBluRay.addActionListener(this);
		rentGame.addActionListener(this);
		returnItem.addActionListener(this);
		dayLate.addActionListener(this);
		fileMenu.addActionListener(this);
		filterTog.addActionListener(this);
		undo.addActionListener(this);

		filterTog.setState(false);
		setJMenuBar(menus);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adds a list to the GUIRentalStore.
		tableDVDs = new TableDVDs();
		jTable = new JTable(tableDVDs);

		// Sets the list selection model for the jTable.
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Adds a JScrollPane for the jTable.
		add(new JScrollPane(jTable));

		// Creates headers for each of the jTable columns.
		jTable.getColumnModel().getColumn(0).setHeaderValue("Customer Name");
		jTable.getColumnModel().getColumn(1).setHeaderValue("Title");
		jTable.getColumnModel().getColumn(2).setHeaderValue("Rented Date");
		jTable.getColumnModel().getColumn(3).setHeaderValue("Due Back Date");
		jTable.getColumnModel().getColumn(4).setHeaderValue("Information");

		tableDVDs.setFilterTog(filterTog);

		setVisible(true);
		setSize(1000,400);
	}

	/*****************************************************************
	 * This method handles event-handling code for the GUIRentalStore
	 * class.
	 * @param e - Holds the action event parameter.
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {

		Object comp = e.getSource();

		// Loads selected files from the JFileChooser.
		if (openSerItem == comp || openTextItem == comp){
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				if (openSerItem == comp)
					tableDVDs.loadDatabase(filename);
				else if(openTextItem == e.getSource())
					tableDVDs.loadFromText(filename);
			}
		}

		// Saves files to the JFileChooser.
		if (saveSerItem == comp || saveTextItem == comp){
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				if (saveSerItem == e.getSource())
					tableDVDs.saveDatabase(filename);
				else if(saveTextItem == e.getSource())
					tableDVDs.saveAsText(filename);
			}
		}

		// Closes the program.
		if(e.getSource() == exitItem){
			System.exit(1);
		}

		// Opens a JDialog box for renting a BluRay.
		if(e.getSource() == rentBluRay){
			DVD bluRay = new BluRay();
			DialogRentBluRay dialog = new DialogRentBluRay(this, bluRay);
			if(dialog.closeOK() && bluRay.dueBack.after(bluRay.rentedOn)){
				tableDVDs.add(bluRay);
			}
		}

		// Opens a JDialog box for renting a video game.
		if(e.getSource() == rentGame){
			DVD game = new Game();
			DialogRentGame dialog = new DialogRentGame(this, game);
			if(dialog.closeOK() && game.dueBack.after(game.rentedOn)){
				tableDVDs.add(game);
			}
		}

		// Opens a JDialog box for returning a disc.
		if (e.getSource() == returnItem){
			returnItem();
		}

		// Opens a JDialog box for displaying all instances of lateness and their details.
		if (e.getSource() == dayLate){
			daysLate();
		}

		// Toggles the dates after the inputted due date to disappear.
		if(filterTog.isSelected()){
			GregorianCalendar date = new GregorianCalendar();
			DialogDaysLate dialog = new DialogDaysLate(this, date, "Filter Date:");

			if(dialog.closeOK()){
				tableDVDs.setDat(date);
				date = tableDVDs.getDat();
				tableDVDs.toggleDates(date);
			}
		}

		// Adds or removes a unit when the button is clicked.
		if(e.getSource() == undo){
			tableDVDs.undo();
		}
	}

	/*****************************************************************
	 * Allows an item to be returned at a specific date and charge
	 * the renter a specified amount.
	 *****************************************************************/
	private void returnItem() {
		// Sets an int when highlighting a specific row of the JTable.
		int index = jTable.getSelectedRow();

		// Checks if no row is selected and warns the user.
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "No Information Has Been Selected");
			return;
		}

		// Creates and instantiates a DVD object.
		DVD temp;
		temp = tableDVDs.getListDVDs().get(index);

		// Creates and instantiates a GregorianCalendar object and JDialog box.
		GregorianCalendar date = new GregorianCalendar();
		DialogDaysLate dialog = new DialogDaysLate(this, date, "Return a Disc:");

		// Checks if the OK button was selected within the JDialog box.
		if(dialog.closeOK()) {
			// Take the parameter from the JDialog box and passes it to the Gregorian Calendar.
			date = dialog.getDayOfTheYear();

			// Checks if the DVD object's return date is after the inputted date, and warns user.
			if(temp.rentedOn.after(date)){
				JOptionPane.showMessageDialog(null, "Cannot Return DVD Before Rented Date");
				return;
			}

			// Formatted string that is shown when an appropriate return occurs.
			JOptionPane.showMessageDialog(null,
					"Thanks " + temp.getNameOfRenter() +
							"\nfor returning " + temp.getTitle() +
							", you owe: " + temp.getCost(date));

			// Removes a unit from the JTable.
			tableDVDs.remove(index);
		}
	}

	/*****************************************************************
	 * Finds all of the units that have a due back date before the
	 * inputted date and show their details.
	 *****************************************************************/
	private void daysLate() {
		// Creates a DVD object and int of days.
		DVD temp;
		int days;

		// Creates and instantiates a GregorianCalendar object and JDialog box.
		GregorianCalendar date = new GregorianCalendar();
		DialogDaysLate dialog = new DialogDaysLate(this, date, "Days Late:");

		// Checks if the OK button was selected within the JDialog box.
		if(dialog.closeOK()) {
			// Creates and instantiates an ArrayList to hold late days.
			ArrayList lateDates = new ArrayList();

			// Take the parameter from the JDialog box and passes it to the Gregorian Calendar.
			date = dialog.getDayOfTheYear();

			// A for loop for iterating all of the objects within the JTable.
			for (int index = 0; index < tableDVDs.getListDVDs().size(); index++){
				temp = tableDVDs.getListDVDs().get(index);

				// Checks if the DVD object's return date is before the inputted date.
				if(temp.dueBack.before(date)) {
					// Subtracts the inputted date from the DVD object's due date to find the difference in days.
					days = date.get(Calendar.DAY_OF_YEAR) - temp.dueBack.get(Calendar.DAY_OF_YEAR);

					// Calls a toString method to display the details about the late days.
					lateDates.add(temp.toString() + ", Days late: " + days + ", Estimated cost: " + temp.getCost(date));
				}
			}
			// Shows an array of all the instances where rentals are considered late and their details.
			JOptionPane.showMessageDialog(this, lateDates.toArray());
		}
	}

	/******************************************************************
	 * Main method that runs the GUIRentalStore class.
	 ******************************************************************/
	public static void main(String[] args) {
		new GUIRentalStore();
	}
}
