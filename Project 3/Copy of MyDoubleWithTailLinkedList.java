package project3;

import java.io.Serializable;

/***********************************************************************
 * The MyDoubleWithTailLinkedList class contains the functions for
 * a double linked list.
 *
 * @author (Kyle Jacobson)
 * @version (11/26/18)
 ***********************************************************************/
public class MyDoubleWithTailLinkedList implements Serializable {

    /** DNode representing the top of the double linked list. */
    private DNode top;

    /** DNode representing the bottom of the double linked list. */
    private DNode tail;

    /** Int representing the size of the double linked list. */
    private int size;

    /***********************************************************************
     * Default Constructor that initializes all instance variables.
     ***********************************************************************/
    public MyDoubleWithTailLinkedList() {
        top = null;
        tail = null;
        size = 0;
    }

    /***********************************************************************
     * Returns the size of the double linked list.
     * @return int size
     ***********************************************************************/
    public int size() {
        // Initializes the size to 0.
        int size = 0;

        // Sets a temporary DNode to top.
        DNode temp = top;

        // Loops through all DNodes that are not null.
        while(temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    /***********************************************************************
     * Clears the double linked list.
     ***********************************************************************/
    public void clear() {
        top = null;
        tail = null;
        size = 0;
    }

    /***********************************************************************
     * Adds new DNodes to top, middle or bottom of the double linked list.
     * Sorts the Games on top and BluRays on bottom.
     * Each section is sorted in order of their due back date.
     ***********************************************************************/
    public void add(DVD s) {
        // No list, insert at the top position.
        if (top == null) {
            tail = top = new DNode(s, null, null);
            size++;
            return;
        }

        // Insert game above all BluRays, only has BluRays in list.
        if (top.getData() instanceof BluRay && s instanceof Game) {
            top = new DNode(s, top, null);
            top.getNext().setPrev(top);
            size++;
            return;
        }

        // Insert game and top of list is a game.
        if (top.getData() instanceof Game && s instanceof Game) {

            // Insert at the top of the list, must adjust top.
            if (top.getData().getRentedOn().after(s.getRentedOn())) {
                top = new DNode(s, top, null);
                top.getNext().setPrev(top);
                size++;
                return;
            }

            // Find in the middle of the list.
            DNode temp = top;
            while (temp != null && (!(temp.getData() instanceof BluRay))) {

                // Insert at Game at the middle of the Game section.
                if (temp.getData().getRentedOn().after(s.getRentedOn())) {
                    temp.setPrev(new DNode(s, temp, temp.getPrev()));
                    temp.getPrev().getPrev().setNext(temp.getPrev());
                    size++;
                    return;
                }
                temp = temp.getNext();
            }

            // Insert at Game at the bottom of the Game section.
            if (temp != null) {
                temp.setPrev(new DNode(s, temp, temp.getPrev()));
                temp.getPrev().getPrev().setNext(temp.getPrev());
                size++;
                return;
            }

            // Go down the list to the node in the list.
            temp = top;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            temp.setNext(new DNode(s, null, temp));
            tail = temp.getNext();
            size++;
            return;
        }

        // Now, if the top is a Game and you want to insert BluRay.
        if (top.getData() instanceof Game && s instanceof BluRay) {

            DNode temp = top;
            while (temp.getNext() != null && (!(temp.getNext().getData() instanceof BluRay))) {
                temp = temp.getNext();
            }

            // There are no BluRays, insert under Game section.
            if (temp.getNext() == null) {
                temp.setNext(new DNode(s, null, temp));
                tail = temp.getNext();
                size++;
                return;
            }

            // Insert BluRay at the top of the BluRay section, but under all games.
            if (temp.getNext().getData().getRentedOn().after(s.getRentedOn())) {
                temp.setNext(new DNode(s, temp.getNext(), temp));
                temp.getNext().getNext().setPrev(temp.getNext());
                size++;
                return;
            }

            // Insert BluRay in the middle of BluRay section.
            temp = temp.getNext();
            while (temp != null && ((temp.getData() instanceof BluRay))) {

                // Finding in the middle, one node past.
                if (temp.getData().getRentedOn().after(s.getRentedOn())) {
                    temp.setPrev(new DNode(s, temp, temp.getPrev()));
                    temp.getPrev().getPrev().setNext(temp.getPrev());
                    size++;
                    return;
                }
                temp = temp.getNext();
            }

            // Insert BluRay at bottom of BluRay section.
            if(s.getRentedOn().after(tail.getData().getRentedOn())){
                tail = new DNode(s,null, tail);
                tail.getPrev().setNext(tail);
                size++;
            }
        }
    }

    /***********************************************************************
     * Removes DNodes from top, middle or bottom of the double linked list.
     * @param index - Location of the DNode in the double linked list.
     * @return DVD temp.getData()
     ***********************************************************************/
    public DVD remove(int index) {
        // Instantiates a temporary DNode.
        DNode temp;

        // Throws an illegalArgumentException if the index is out of bounds.
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("Index is out of bounds!");
        }

        // Throws a nullPointerException if the list is empty.
        if (size() == 0) {
            throw new NullPointerException("List is empty!");
        }

        // Removes when there is only one item in the list.
        if (size == 1) {
            temp = top;
            top = tail = null;
            size = 0;
            return temp.getData();
        }

        // Removes an item from the top of the list.
        if (index == 0) {
            temp = top;
            top = top.getNext();
            top.setPrev(null);
            size--;
            return temp.getData();
        }

        // Removes an item from the bottom of the list.
        if (index == size - 1) {
            temp = tail;
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
            return temp.getData();
        }

        // Removes an item from the middle of the list.
        DNode tempAbove;
        DNode tempBelow;

        int i = 1;
        tempAbove = top;
        while (i < index) {
            tempAbove = tempAbove.getNext();
            i++;
        }

        temp = tempAbove.getNext();
        tempBelow = tempAbove.getNext().getNext();
        tempAbove.setNext(tempBelow);
        tempBelow.setPrev(tempAbove);
        size--;
        return temp.getData();
    }

    /***********************************************************************
     * Gets the data from a specific DNode in the double linked list.
     * @param index - Location of the DNode in the double linked list.
     * @return DVD temp.getData()
     ***********************************************************************/
    public DVD get(int index) {
        // Sets a temporary Node to top.
        DNode temp = top;

        // Loops through the entire double linked list.
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /***********************************************************************
     * Returns a String that is formatted for display purposes.
     * @return String - Various details about the double linked list.
     ***********************************************************************/
    @Override
    public String toString() {
        return "MyDoubleWithTailLinkedList{" +
                "top=" + top +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
