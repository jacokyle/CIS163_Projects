package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;

/***********************************************************************
 * The ChangeMaker class contains the methods to operate the Change Maker.
 *
 * @author (Kyle Jacobson)
 * @version (09/14/18)
 ***********************************************************************/

public class ChangeMaker {

    /** boolean for checking if quarters are available */
    private static boolean quartersAvail = true;

    /** boolean for checking if dimes are available */
    private static boolean dimesAvail = true;

    /** boolean for checking if nickels are available */
    private static boolean nickelsAvail = true;

    /** boolean for checking if pennies are available */
    private static boolean penniesAvail = true;

    /** double for amount of money*/
    private double amount;

    /***********************************************************************
     * Default Constructor that sets ChangeMaker to zero.
     ***********************************************************************/
    public ChangeMaker() {
        amount = 0;

        quartersAvail = false;
        dimesAvail = false;
        nickelsAvail = false;
        penniesAvail = false;
    }

    /***********************************************************************
     * Constructor that initializes the instance variables with
     * other ChangeMaker parameters.
     * @param other =
     ***********************************************************************/
    public ChangeMaker(ChangeMaker other) {
        this.amount = other.amount;
    }

    /***********************************************************************
     * Constructor that initializes the instance variables with the
     * amount parameter.
     * @param amount - Amount parameter passed to the Change Maker.
     * @throws IllegalArgumentException if amount is less than 0.
     * @throws IllegalArgumentException if amount has more than 2 decimals.
     * @throws IllegalArgumentException if amount is greater than 1.0E15.
     ***********************************************************************/
    public ChangeMaker(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        if (Math.floor(amount * 100) / 100 != (amount * 100) / 100)
            throw new IllegalArgumentException("Amount must have at least 2 decimals");

        if (amount >= 1.0E15)
            throw new IllegalArgumentException("Amount must be less than 1.0E15");

        this.amount = amount;
    }

    /***********************************************************************
     * Returns the boolean value for quartersAvail.
     * @return boolean quartersAvail
     ***********************************************************************/
    public static boolean isQuartersAvail() {
        return quartersAvail;
    }

    /***********************************************************************
     * Assigns the parameter dimesAvail to ChangeMaker.dimesAvail.
     * @param quartersAvail - true when quarters are available, otherwise false.
     ***********************************************************************/
    public static void setQuartersAvail(boolean quartersAvail) {
        ChangeMaker.quartersAvail = quartersAvail;
    }

    /***********************************************************************
     * Returns the boolean value for dimesAvail.
     * @return boolean dimesAvail
     ***********************************************************************/
    public static boolean isDimesAvail() {
        return dimesAvail;
    }

    /***********************************************************************
     * Assigns the parameter dimesAvail to ChangeMaker.dimesAvail.
     * @param dimesAvail - true when dimes are available, otherwise false.
     ***********************************************************************/
    public static void setDimesAvail(boolean dimesAvail) {
        ChangeMaker.dimesAvail = dimesAvail;
    }

    /***********************************************************************
     * Returns the boolean value for nickelsAvail.
     * @return boolean nickelsAvail
     ***********************************************************************/
    public static boolean isNickelsAvail() {
        return nickelsAvail;
    }

    /***********************************************************************
     * Assigns the parameter dimesAvail to ChangeMaker.dimesAvail.
     * @param nickelsAvail - true when nickels are available, otherwise false.
     ***********************************************************************/
    public static void setNickelsAvail(boolean nickelsAvail) {
        ChangeMaker.nickelsAvail = nickelsAvail;
    }

    /***********************************************************************
     * Returns the boolean value for penniesAvail.
     * @return boolean penniesAvail
     ***********************************************************************/
    public static boolean isPenniesAvail() {
        return penniesAvail;
    }

    /***********************************************************************
     * Assigns the parameter dimesAvail to ChangeMaker.dimesAvail.
     * @param penniesAvail - true when pennies are available, otherwise false.
     ***********************************************************************/
    public static void setPenniesAvail(boolean penniesAvail) {
        ChangeMaker.penniesAvail = penniesAvail;
    }

    /***********************************************************************
     * Returns the double value for amount.
     * @return double amount
     ***********************************************************************/
    public double getAmount() {
        return amount;
    }

    /***********************************************************************
     * Assigns the parameter dimesAvail to ChangeMaker.dimesAvail.
     * @param amount - value for amount of money
     ***********************************************************************/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /***********************************************************************
     * Returns true if ChangeMaker object is same as Object other, otherwise false.
     * @param other - Parameter object that is being compared to Object object.
     * @return boolean true - When ChangeMaker other is the same as Object other.
     * @return boolean false - When ChangeMaker other is NOT the same as Object other.
     ***********************************************************************/
    public boolean equals(Object other) {
        if (other != null) {
            if (other instanceof ChangeMaker) {
                return !(this.amount != ((ChangeMaker) other).amount);
            }
        }
        return true;
    }

    /***********************************************************************
     * Returns true if ChangeMaker object is same as another ChangeMaker other, otherwise false.
     * @param other - Parameter object that represents a ChangeMaker object.
     * @return boolean true - When ChangeMaker other is the same as another ChangeMaker other.
     * @return boolean false - When ChangeMaker other is NOT the same as ChangeMaker other.
     ***********************************************************************/
    public boolean equals(ChangeMaker other) {
        if (other != null) {
            return !(this.amount != other.amount);
        }
        return true;
    }

    /***********************************************************************
     * Returns true if two ChangeMaker objects are equal, otherwise false.
     * @param other1 - Parameter object that represents first ChangeMaker object.
     * @param other2 - Parameter object that represents second ChangeMaker object.
     * @return true - When both ChangeMaker objects are the same.
     * @return false - When both ChangeMaker objects are NOT the same.
     ***********************************************************************/
    public static boolean equals(ChangeMaker other1, ChangeMaker other2) {
        if (other1 != null && other2 != null) {
            return !(other1.amount != other2.amount);
        }
        return true;
    }

    /***********************************************************************
     * Returns an integer when the current ChangeMaker amount is less than,
     * greater than or equal to the ChangeMaker object passed as a parameter.
     * @param other - Parameter object that represents first ChangeMaker object.
     * @return int 1 - When the first ChangeMaker amount is less than the parameter.
     * @return int -1 - When the first ChangeMaker amount is greater than the parameter.
     * @return int 0 - When both ChangeMaker amounts are the same.
     ***********************************************************************/
    public int compareTo(ChangeMaker other) {
        return Double.compare(this.amount, other.amount);
    }

    /***********************************************************************
     * Returns an integer when two ChangeMaker amounts are compared and the first
     * ChangeMaker object is less than, greater than or equal to the second ChangeMaker object.
     * @param other1 - Parameter object that represents first ChangeMaker object.
     * @param other2 - Parameter object that represents second ChangeMaker object.
     * @return int 1 - When the first ChangeMaker amount is less than the second.
     * @return int -1 - When the first ChangeMaker amount is greater than the second.
     * @return int 0 - When both ChangeMaker amounts are the same.
     ***********************************************************************/
    public static int compareTo(ChangeMaker other1, ChangeMaker other2) {
        return Double.compare(other1.amount, other2.amount);

    }

    /***********************************************************************
     * Adds the parameter amount to the amount of money already in the Change Maker.
     * @param amount - Amount of money that will be added to the machine.
     * @throws IllegalArgumentException if amount is less than 0.
     * @throws IllegalArgumentException if amount has more than 2 decimals.
     * @throws IllegalArgumentException if amount is greater than 1.0E15.
     ***********************************************************************/
    public void loadMachine(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        if (Math.floor(amount * 100) / 100 != (amount * 100) / 100)
            throw new IllegalArgumentException("Amount must have at least 2 decimals");

        if (amount >= 1.0E15)
            throw new IllegalArgumentException("Amount must be less than 1.0E15");

        this.amount = this.amount + amount;
    }

    /***********************************************************************
     * Adds the parameter amount to the amount of money already in the Change Maker.
     * @param otherAmount - Amount of money that will be added to the machine.
     * @throws IllegalArgumentException if amount is less than 0.
     * @throws IllegalArgumentException if amount has more than 2 decimals.
     * @throws IllegalArgumentException if amount is greater than 1.0E15.
     ***********************************************************************/
    public void takeOut(Double otherAmount) {
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;
        int numPennies;
        int pennies;

        if (otherAmount < 0)
            throw new IllegalArgumentException("Amount must be greater than 0");

        if (Math.floor(otherAmount * 100) != otherAmount * 100)
            throw new IllegalArgumentException("Amount must have at least 2 decimals");

        if (otherAmount > 1.0E15)
            throw new IllegalArgumentException("Amount must be less than 1.0E15");

        amount = amount - otherAmount;
        pennies = (int) amount * 100;

        if(isQuartersAvail()){
            numQuarters = pennies / 25;
            pennies = pennies - (numQuarters * 25);
        }

        if(isDimesAvail()){
            numDimes = pennies / 10;
            pennies = pennies - (numDimes * 10);
        }

        if(isNickelsAvail()){
            numNickels = pennies / 5;
            pennies = pennies - (numNickels * 5);
        }

        if(isPenniesAvail()){
            numPennies = pennies;
            pennies = pennies - numPennies;
        }

        new ChangeBag(numQuarters, numDimes, numNickels, pennies);
    }

    /***********************************************************************
     * Returns a string that represents the currency in the Change Maker.
     * @return String - Amount that is formatted with currency standards.
     ***********************************************************************/
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(getAmount());

        return moneyString;
    }

    /***********************************************************************
     * Method that saves the current ChangeMaker to a file.
     * @param fileName - Name that represents the current file.
     * @throws IllegalArgumentException if save operation was halted.
     ***********************************************************************/
    public void save(String fileName) {
        PrintWriter out;

        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Save operation could not be completed");
        }
        out.println(amount);
        out.close();
    }

    /***********************************************************************
     * Method that loads the current ChangeMaker to the Change Maker.
     * @param fileName - Name that represents the current file.
     * @throws IllegalArgumentException if load operation was halted.
     ***********************************************************************/
    public void load(String fileName) {
        try{

            // open the data file
            Scanner fileReader = new Scanner(new File(fileName));

            // read one double
            amount = fileReader.nextDouble();
            System.out.println(amount);
        }

        // problem reading the file
        catch(Exception error){
            throw new IllegalArgumentException("Load operation could not be completed");
        }
    }
}