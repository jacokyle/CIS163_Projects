package project3;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/***********************************************************************
 * The Game class contains information about a video game.
 *
 * @author (Kyle Jacobson)
 * @version (11/26/18)
 ***********************************************************************/
public class Game extends DVD {

	/** PlayerType for representing the type of video game platform. */
	private PlayerType player;

	/***********************************************************************
	 * Returns the cost of the DVD (Game) item being rented.
	 * @param ActualDatRented - Date that is inputted by the user.
	 ***********************************************************************/
	public double getCost(GregorianCalendar ActualDatRented) {
		// Returns the cost that was calculated in the getCost method of the DVD class.
		return super.getCost(ActualDatRented);
	}

	/***********************************************************************
	 * Default Constructor that pulls information from the super class.
	 ***********************************************************************/
	public Game() {
        super();
	}

	/***********************************************************************
	 * Constructor that initializes the instance variables with the
	 * Game parameters.
	 * @param rentedOn - Rental date parameter passed to the DVD (Game).
	 * @param dueBack - Return date parameter passed to the DVD (Game).
	 * @param title - Item title parameter passed to the DVD (Game).
	 * @param name - Name of renter parameter passed to the DVD (Game).
	 * @param player - Platform parameter passed to the DVD (Game).
	 ***********************************************************************/
	public Game(GregorianCalendar rentedOn, GregorianCalendar dueBack,
			String title, String name, PlayerType player) {
		// Passes the parameters from the DVD class.
		super(rentedOn, dueBack, title, name);

		// Sets the player parameter to this.player.
		this.player = player;
	}

	/***********************************************************************
	 * Returns the PlayerType enum for getPlayer.
	 * @return PlayerType player
	 ***********************************************************************/
	public PlayerType getPlayer() {
		return player;
	}

	/***********************************************************************
	 * Assigns the parameter player to this.player.
	 * @param player - Video game platform that is selected during rental.
	 ***********************************************************************/
	public void setPlayer(PlayerType player) {
		this.player = player;
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
