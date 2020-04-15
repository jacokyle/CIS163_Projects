package project3;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/***********************************************************************
 * The BluRay class contains information about a BluRay disc.
 *
 * @author (Kyle Jacobson)
 * @version (10/18/18)
 ***********************************************************************/
public class BluRay extends DVD {

    /** Boolean for representing the layering of the BluRay. */
    private boolean doubleLayer;

    /***********************************************************************
     * Returns the cost of the DVD (BluRay) item being rented.
     * @param ActualDatRented - Date that is inputted by the user.
     ***********************************************************************/
    public double getCost(GregorianCalendar ActualDatRented) {
        // Returns the cost that was calculated in the getCost method of the DVD class.
        return super.getCost(ActualDatRented);
    }

    /***********************************************************************
     * Default Constructor that pulls information from the super class.
     ***********************************************************************/
    public BluRay() {
        super();
    }

    /***********************************************************************
     * Constructor that initializes the instance variables with the
     * BluRay parameters.
     * @param rentedOn - Rental date parameter passed to the DVD (BluRay).
     * @param dueBack - Return date parameter passed to the DVD (BluRay).
     * @param title - Item title parameter passed to the DVD (BluRay).
     * @param name - Name of renter parameter passed to the DVD (BluRay).
     * @param doubleLayer - Layer parameter passed to the DVD (BluRay).
     ***********************************************************************/
    public BluRay(GregorianCalendar rentedOn, GregorianCalendar dueBack,
                  String title, String name, boolean doubleLayer) {
        // Passes the parameters from the DVD class.
        super(rentedOn, dueBack, title, name);

        // Sets the doubleLayer parameter to this.doubleLayer.
        this.doubleLayer = doubleLayer;
    }

    /***********************************************************************
     * Returns the boolean value for isDoubleLayer.
     * @return boolean doubleLayer
     ***********************************************************************/
    public boolean isDoubleLayer() {
        return doubleLayer;
    }

    /***********************************************************************
     * Assigns the parameter doubleLayer to this.doubleLayer.
     * @param doubleLayer - True when BluRay is doubleLayered, otherwise false.
     ***********************************************************************/
    public void setDoubleLayer(boolean doubleLayer) {
        this.doubleLayer = doubleLayer;
    }

    /***********************************************************************
     * Returns a String that is formatted for display purposes.
     * @return String - Various details about the rental situation.
     ***********************************************************************/
    @Override
    public String toString() {
        // Creates a SimpleDateFormat object with a specific format setup.
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        // Returns a formatted String for display purposes.
        return "Renter's Name: " + nameOfRenter +
                ", Title: " + title +
                ", Due Back on " + df.format(dueBack.getTime());
    }
}
