package project3;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Stack;

/***********************************************************************
 * The TableDVDs class organizes the inputted information onto a JTable
 * that can be displayed on the GUI.
 *
 * @author (Kyle Jacobson)
 * @version (11/26/18)
 ***********************************************************************/
public class TableDVDs extends AbstractTableModel {

    /** MyDoubleWithTailLinkedList for representing the list of DVDs. */
    private MyDoubleWithTailLinkedList listDVDs;

    /** GregorianCalendar object for representing the filter date. */
    private GregorianCalendar dat;

    /** JCheckBoxMenuItem button used for filtering the units by date. */
    private JCheckBoxMenuItem filterTog;

    /** Stack used for undoing the add and remove functions. */
    private Stack<DVD> rentRecord;

    /** Stack used for tracking add and remove inputs. */
    private Stack<Character> inputRecord;

    /*******************************************************************
     * Default Constructor that pulls information from the super class.
     *******************************************************************/
    public TableDVDs() {
        super();
        listDVDs = new MyDoubleWithTailLinkedList();
        rentRecord = new Stack<DVD>();
        inputRecord = new Stack<Character>();
        dat = new GregorianCalendar();
        createList();
    }

    /***********************************************************************
     * Returns the int value for the total number of columns on jTable.
     * @return int 5
     ***********************************************************************/
    public int getColumnCount() {
        return  5;
    }

    /***********************************************************************
     * Returns the int value for the total number of rows on jTable.
     * @return int listDVDs.size()
     ***********************************************************************/
    @Override
    public int getRowCount() {
        return listDVDs.size();
    }

    /***********************************************************************
     * Returns a MyDoubleWithTailLinkedList of DVDs from the jTable.
     * @return MyDoubleWithTailLinkedList listDVDs
     ***********************************************************************/
    public MyDoubleWithTailLinkedList getListDVDs() {
        return listDVDs;
    }

    /***********************************************************************
     * Returns a GregorianCalendar object.
     * @return GregorianCalendar dat
     ***********************************************************************/
    public GregorianCalendar getDat() {
        return dat;
    }

    /***********************************************************************
     * Assigns the parameter dat to this.dat.
     * @param dat - GregorianCalendar parameter inputted by user.
     ***********************************************************************/
    public void setDat(GregorianCalendar dat) {
        this.dat = dat;
    }

    /***********************************************************************
     * Assigns the parameter filterTog to this.filterTog.
     * @param filterTog - JCheckBoxMenuItem button passed as a parameter.
     ***********************************************************************/
    public void setFilterTog(JCheckBoxMenuItem filterTog) {
        this.filterTog = filterTog;
    }

    /***********************************************************************
     * Returns an Object that is present in each of the cells on jTable.
     * @param row - Int parameter for representing the row in jTable.
     * @param col - Int parameter for representing the column in jTable.
     * @return listDVDs Object
     * @return null
     ***********************************************************************/
    @Override
    public Object getValueAt(int row, int col) {
        // Checks if toggleDates is true and if dueBack date is after the inputted date.
        if (toggleDates(dat) && listDVDs.get(row).dueBack.after(dat)) {
            fireTableCellUpdated(row, col);
            return "******";
        }

        // Switch designed for returning information about DVD throughout JTable.
        switch (col) {

            // Column is filled with the name of renter associated with DVD.
            case 0:
                return (listDVDs.get(row).getNameOfRenter());

            // Column is filled with the title associated with DVD.
            case 1:
                return (listDVDs.get(row).getTitle());

            // Column is filled with the rental date associated with DVD.
            case 2:
                return (DateFormat.getDateInstance(DateFormat.SHORT)
                        .format(listDVDs.get(row).getRentedOn().getTime()));

            // Column is filled with the return date associated with DVD.
            case 3:
                return (DateFormat.getDateInstance(DateFormat.SHORT)
                        .format(listDVDs.get(row).getDueBack().getTime()));

            // Column is filled with the Game/BluRay details associated with DVD.
            case 4:
                if (listDVDs.get(row) instanceof Game)
                    return "Game - Required Player: " + ((Game) listDVDs.get(row)).getPlayer().toString();

                else if (listDVDs.get(row) instanceof BluRay)
                    return "BluRay - Double Layered: " + ((BluRay) listDVDs.get(row)).isDoubleLayer();

            default:
                return null;
        }
    }

    /***********************************************************************
     * Hides any units with a due date after the inputted date.
     * @return boolean true - When toggleDates is activated.
     * @return boolean false - When toggleDates is deactivated.
     ***********************************************************************/
    public boolean toggleDates(GregorianCalendar dat) {
        // If the filterTog JCheckBoxMenuItem is selected, filter the dates.
        if(filterTog.isSelected()) {
            return true;
        }
        return false;
    }

    /***********************************************************************
     * Removes a DVD unit from the jTable.
     * @param i - Int parameter for representing the MyDoubleWithTailLinkedList
     *          index.
     * @return DVD unit
     ***********************************************************************/
    public DVD remove(int i) {
        // Adds an element to each stack.
        rentRecord.push(listDVDs.get(i));
        inputRecord.push('R');

        // Removes a unit from the JTable.
        DVD unit = listDVDs.remove(i);

        // Allows the JTable information to visibly change.
        fireTableDataChanged();

        // Return the unit of DVD.
        return unit;
    }

    /***********************************************************************
     * Removes a DVD for the stack in undo method.
     * @param a - DVD object that is removed from the
     *          MyDoubleWithTailLinkedList.
     * @return DVD unit
     ***********************************************************************/
    public DVD removeForUndo(DVD a){
        // Int used for iterating through indexes.
        int i = 0;

        // Loop used for checking if DVD at index is equal to parameter.
        while(!listDVDs.get(i).equals(a))
            i++;

        // Adds a unit to the stack.
        DVD unit = listDVDs.remove(i);

        // Allows the JTable information to visibly change.
        fireTableDataChanged();

        // Returns the unit.
        return unit;
    }

    /***********************************************************************
     * Adds a DVD unit to the jTable.
     * @param a - DVD object that is added to the MyDoubleWithTailLinkedList.
     ***********************************************************************/
    public void add(DVD a) {
        // Adds a unit to the JTable.
        listDVDs.add(a);

        // Adds an element to each stack.
        rentRecord.push(a);
        inputRecord.push('A');

        // Allows the JTable information to visibly change.
        fireTableDataChanged();
    }

    /***********************************************************************
     * Adds a DVD for the stack in the undo method.
     * @param a - DVD object that is added to the MyDoubleWithTailLinkedList.
     ***********************************************************************/
    public void addForUndo(DVD a){
        // Adds a unit to the stack.
        listDVDs.add(a);

        // Allows the JTable information to visibly change.
        fireTableDataChanged();
    }

    /*******************************************************************
     * Adds or removes any unit that was recently changed.
     ******************************************************************/
    public void undo() {
        // Checks if it is possible to use the undo button.
        if (inputRecord.size() < 1) {
            JOptionPane.showMessageDialog(null, "Undo is not possible!");
            return;
        }

        // Assigns the inputRecord element to a char.
        char temp = inputRecord.pop();

        // Undoes a remove function.
        if(temp == 'R')
            addForUndo(rentRecord.pop());

        // Undoes an add function.
        if(temp == 'A')
            removeForUndo(rentRecord.pop());

        // Allows the JTable information to visibly change.
        fireTableDataChanged();
    }

    /***********************************************************************
     * Saves (serializes) the Account objects to a specified file.
     * @param filename - autoName of the file to save to.
     ***********************************************************************/
    public void saveDatabase(String filename) {
        // Tries to use various OutputStreams to save a file.
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listDVDs);
            os.close();
        }

        // Catches an error when there is an IOException while saving database.
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Error in saving database.");
        }
    }

    /*******************************************************************
     * Loads (deserialize) the Account objects from the specified file.
     * @param filename - autoName of the file to load from.
     ******************************************************************/
    public void loadDatabase(String filename) {
        // Tries to use various InputStreams to load a file.
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            listDVDs = (MyDoubleWithTailLinkedList) is.readObject();
            fireTableDataChanged();
            is.close();
        }

        // Catches an error when there is an Exception while loading database.
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error in loading database.");
        }
    }

    /***********************************************************************
     * Saves the information as a text file.
     * @param filename - autoName of the file to save to.
     * @return boolean true
     ***********************************************************************/
    public boolean saveAsText(String filename) {
        // Creates an object of the PrintWriter class.
        PrintWriter out;

        // Tries to pass a filename to the object using multiple Writers.
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        }

        // Catches an error when there is an Exception while saving the text file.
        catch (Exception e) {
            throw new IllegalArgumentException("Save operation could not be completed.");
        }
        out.println(filename);
        out.close();
        return true;
    }

    /***********************************************************************
     * Loads the information from a text file.
     * @param filename - autoName of the file to load from.
     ***********************************************************************/
    public void loadFromText(String filename) {
        try{
            // Opens the data file.
            Scanner fileReader = new Scanner(new File(filename));

            // Reads one string.
            filename = fileReader.next();
            System.out.println(filename);
        }

        // Catches an error when there is an Exception while loading the text file.
        catch(Exception error){
            throw new IllegalArgumentException("Load operation could not be completed.");
        }
    }

    /***********************************************************************
     * Creates a list that is loaded by default upon execution of the GUI.
     ***********************************************************************/
    public void createList() {

        // Formats and creates several GregorianCalendar objects.
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar temp1 = new GregorianCalendar();
        GregorianCalendar temp2 = new GregorianCalendar();
        GregorianCalendar temp3 = new GregorianCalendar();
        GregorianCalendar temp4 = new GregorianCalendar();
        GregorianCalendar temp5 = new GregorianCalendar();
        GregorianCalendar temp6 = new GregorianCalendar();

        // Tries to create several default instances of DVD units.
        try {
            // Creates several dates that are parsed and set on the JTable.
            Date d1 = df.parse("3/20/2018");
            temp1.setTime(d1);
            Date d2 = df.parse("4/20/2018");
            temp2.setTime(d2);
            Date d3 = df.parse("12/20/2018");
            temp3.setTime(d3);
            Date d4 = df.parse("1/20/2018");
            temp4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            temp5.setTime(d5);
            Date d6 = df.parse("1/20/2020");
            temp6.setTime(d6);

            // Creates three games and three BluRay units that are displayed on the JTable.
            Game game1 = new Game(temp1, temp2, "Title Game1", "Name1" , PlayerType.Xbox360);
            Game game2 = new Game(temp5, temp3, "Title Game2", "Name2" , PlayerType.XboxOne);
            Game game3 = new Game(temp1, temp6, "Title Game3", "Name3" , PlayerType.PC);
            BluRay bluRay1 = new BluRay(temp1, temp3, "Title BluRay1", "Name4" , true);
            BluRay bluRay2 = new BluRay(temp5, temp2, "Title BluRay2", "Name5" , false);
            BluRay bluRay3 = new BluRay(temp5, temp1, "Title BluRay3", "Name6" , true);

            // Adds the created objects from above to the JTable.
            add(game1);
            add(game2);
            add(game3);
            add(bluRay1);
            add(bluRay2);
            add(bluRay3);
        }

        // Catches an error when there is a ParseException when creating the default list.
        catch (ParseException e) {
            throw new RuntimeException("There was an issue while testing and creating the list.");
        }
    }
}


