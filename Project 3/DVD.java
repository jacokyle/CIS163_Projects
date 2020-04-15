package project3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/***********************************************************************
 * The DVD class contains information about a DVD.
 *
 * @author (Kyle Jacobson)
 * @version (11/26/18)
 ***********************************************************************/
public class DVD implements Serializable {

	/** Long for representing the serial version UID. */
	private static final long serialVersionUID = 1L;

	/** GregorianCalendar for representing the rental date. */
	protected GregorianCalendar rentedOn;

	/** GregorianCalendar for representing the due back date. */
	protected GregorianCalendar dueBack;

	/** String for representing the title of the rented object */
	protected String title;

	/** String for representing the name of the person renting the DVD. */
	protected String nameOfRenter;

	/***********************************************************************
	 * Returns the cost of the DVD item being rented.
	 * @param ActualDatRented - Date that is inputted by the user.
	 * @return double cost
	 ***********************************************************************/
	public double getCost(GregorianCalendar ActualDatRented) {
		double cost;

		//		Date date = Calendar.getInstance().getTime();	// get today's date.
//
//		Calendar c = Calendar.getInstance();
//		c.setTime(soldOn.getTime());
//		c.add(Calendar.DATE, 1);  // number of days to add to make it 12am next day.
//		soldOn.setTime(c.getTime());

		Calendar dueBackTemp = Calendar.getInstance();
		dueBackTemp.setTime(dueBack.getTime());
		if (ActualDatRented.before(dueBack))
			cost = 3;
		else {
		    int count = 0;
			while (dueBackTemp.before(ActualDatRented)) {
				dueBackTemp.add(Calendar.DATE, 1);
				count++;
			}
            cost = Math.min(3 + count, 50);
		}

		return cost;
	}

	/***********************************************************************
	 * Default Constructor for the DVD class.
	 ***********************************************************************/
	public DVD() {
	}

	/***********************************************************************
	 * Constructor that initializes the instance variables with the
	 * DVD parameters.
	 * @param rentedOn - Rental date parameter passed to the DVD (Game).
	 * @param dueBack - Return date parameter passed to the DVD (Game).
	 * @param title - Item title parameter passed to the DVD (Game).
	 * @param name - Name of renter parameter passed to the DVD (Game).
	 ***********************************************************************/
	public DVD(GregorianCalendar rentedOn, GregorianCalendar dueBack, String title, String name) {
		super();
		this.rentedOn = rentedOn;
		this.dueBack = dueBack;
		this.title = title;
		this.nameOfRenter = name;
	}

	/***********************************************************************
	 * Returns the GregorianCalendar rentedOn for getRentedOn.
	 * @return GregorianCalendar rentedOn
	 ***********************************************************************/
	public GregorianCalendar getRentedOn() {
		return rentedOn;
	}

	/***********************************************************************
	 * Assigns the parameter opened to this.rentedOn.
	 * @param opened - Date of rental.
	 ***********************************************************************/
	public void setRentedOn(GregorianCalendar opened) {
		this.rentedOn = opened;
	}

	/***********************************************************************
	 * Returns the GregorianCalendar dueBack for getDueBack.
	 * @return GregorianCalendar dueBack
	 ***********************************************************************/
	public GregorianCalendar getDueBack() {
		return dueBack;
	}

	/***********************************************************************
	 * Assigns the parameter dueBack to this.dueBack.
	 * @param dueBack - Date of return.
	 ***********************************************************************/
	public void setDueBack(GregorianCalendar dueBack) {
		this.dueBack = dueBack;
	}

	/***********************************************************************
	 * Returns the String title for getTitle.
	 * @return String title
	 ***********************************************************************/
	public String getTitle() {
		return title;
	}

	/***********************************************************************
	 * Assigns the parameter title to this.title.
	 * @param title - Name of the DVD.
	 ***********************************************************************/
	public void setTitle(String title) {
		this.title = title;
	}

	/***********************************************************************
	 * Returns the String nameOfRenter for getNameOfRenter.
	 * @return String nameOfRenter
	 ***********************************************************************/
	public String getNameOfRenter() {
		return nameOfRenter;
	}

	/***********************************************************************
	 * Assigns the parameter nameOfRenter to this.nameOfRenter.
	 * @param nameOfRenter - Name of the person renting the DVD.
	 ***********************************************************************/
	public void setNameOfRenter(String nameOfRenter) {
		this.nameOfRenter = nameOfRenter;
	}
}
