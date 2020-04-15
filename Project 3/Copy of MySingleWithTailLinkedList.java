package project3;

import java.io.Serializable;

/***********************************************************************
 * The MySingleWithTailLinkedList class contains the functions for
 * a single linked list.
 *
 * @author (Kyle Jacobson)
 * @version (11/26/18)
 ***********************************************************************/
public class MySingleWithTailLinkedList implements Serializable {

    /** Node representing the top of the single linked list. */
    private Node top;

    /** Node representing the bottom of the single linked list. */
    private Node tail;

    /** Int representing the size of the single linked list. */
    public int size;

    /***********************************************************************
     * Default Constructor that initializes all instance variables.
     ***********************************************************************/
    public MySingleWithTailLinkedList() {
        top = null;
        tail = null;
        size = 0;
    }

    /***********************************************************************
     * Returns the size of the single linked list.
     * @return int size
     ***********************************************************************/
    public int size() {
        // Initializes the size to 0.
        int size = 0;

        // Sets a temporary Node to top.
        Node temp = top;

        // Loops through all Nodes that are not null.
        while(temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    /***********************************************************************
     * Clears the single linked list.
     ***********************************************************************/
    public void clear() {
        top = null;
        tail = null;
        size = 0;
    }

    /***********************************************************************
     * Adds new Nodes to top, middle or bottom of the single linked list.
     * Sorts the Games on top and BluRays on bottom.
     * Each section is sorted in order of their due back date.
     ***********************************************************************/
    public void add(DVD s) {
        // No list, insert at the top position.
        if (top == null) {
            tail = top = new Node(s, null);
            size++;
            return;
        }

        // Insert game above all BluRays, only has BluRays in list.
        if (top.getData() instanceof BluRay && s instanceof Game) {
            top = new Node(s, top);
            size++;
            return;
        }

        // Insert game and top of list is a game.
        if (top.getData() instanceof Game && s instanceof Game) {

            // Insert Game at the top of the Game section.
            if (top.getData().getRentedOn().after(s.getRentedOn())) {
                top = new Node(s, top);
                size++;
                return;
            }

            // Find in the middle of the list.
            int i = -1;
            Node temp = top;
            while (temp != null && (!(temp.getData() instanceof BluRay))) {

                // Insert Game at the middle of the Game section.
                if (temp.getData().getRentedOn().after(s.getRentedOn())) {
                    temp = top;
                    int j = 0;
                    while (j < i) {
                        temp = temp.getNext();
                        j++;
                    }
                    temp.setNext(new Node(s, temp.getNext()));
                    size++;
                    return;
                }
                temp = temp.getNext();
                i++;
            }

            // Insert Game at the bottom of the Game section.
            if (temp != null) {
                temp = top;
                int j = 0;
                while (j < i) {
                    temp = temp.getNext();
                    j++;
                }
                temp.setNext(new Node(s, temp.getNext()));
                size++;
                return;
            }

            // Go down the list to the bottom of the Game section, no BluRays.
            temp = top;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(s, null));
            tail = temp.getNext();
            size++;
            return;
        }

        // Now, if the top is a Game and you want to insert BluRay.
        if (top.getData() instanceof Game && s instanceof BluRay) {

            Node temp = top;
            while (temp.getNext() != null && (!(temp.getNext().getData() instanceof BluRay))) {
                temp = temp.getNext();
            }

            // There are no BluRays, insert under Game section.
            if (temp.getNext() == null) {
                temp.setNext(new Node(s, null));
                tail = temp.getNext();
                size++;
                return;
            }

            // Insert BluRay at the top of the BluRay section, but under all games.
            if (temp.getNext().getData().getRentedOn().after(s.getRentedOn())) {
                temp.setNext(new Node(s, temp.getNext()));
                size++;
                return;
            }

            // Find in the middle of the list.
            int i = -1;
            temp = temp.getNext();
            Node temp2 = temp;
            while (temp2 != null && ((temp2.getData() instanceof BluRay))) {

                // Insert BluRay at the middle of the Game section.
                if (temp2.getData().getRentedOn().after(s.getRentedOn())) {
                    int j = 0;
                    while (j < i) {
                        temp = temp.getNext();
                        j++;
                    }
                    temp.setNext(new Node(s, temp.getNext()));
                    size++;
                    return;
                }
                temp2 = temp2.getNext();
                i++;
            }

            // Insert BluRay at bottom of BluRay section.
            if(s.getRentedOn().after(tail.getData().getRentedOn())){
                tail.setNext(new Node(s, null));
                tail = tail.getNext();
                size++;
            }
        }
    }


    /***********************************************************************
     * Removes Nodes from top, middle or bottom of the single linked list.
     * @param index - Location of the Node in the single linked list.
     * @return DVD temp.getData()
     ***********************************************************************/
    public DVD remove(int index) {
        // Instantiates Nodes.
        Node temp;
        Node tempAbove;
        Node tempBelow;

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
            size--;
            return temp.getData();
        }

        // Removes an item from the bottom of the list.
        if (index == size - 1) {
            temp = tail;
            tempAbove = top;
            int i = 1;
            while(i < index){
                tempAbove = tempAbove.getNext();
                i++;
            }
            tail = tempAbove;
            tail.setNext(null);
            size--;
            return temp.getData();
        }

        // Removes an item from the middle of the list.
        int i = 1;
        tempAbove = top;
        while (i < index) {
            tempAbove = tempAbove.getNext();
            i++;
        }

        temp = tempAbove.getNext();
        tempBelow = tempAbove.getNext().getNext();
        tempAbove.setNext(tempBelow);
        size--;
        return temp.getData();
    }

    /***********************************************************************
     * Gets the data from a specific Node in the single linked list.
     * @param index - Location of the Node in the single linked list.
     * @return DVD temp.getData()
     ***********************************************************************/
    public DVD get(int index) {
        // Sets a temporary Node to top.
        Node temp = top;

        // Loops through the entire single linked list.
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /***********************************************************************
     * Returns a String that is formatted for display purposes.
     * @return String - Various details about the single linked list.
     ***********************************************************************/
    @Override
    public String toString() {
        return "MySingleWithTailLinkedList{" +
                "top=" + top +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}