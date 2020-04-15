package project3.LinkedListClassNotes2;

public class DLinkListFirstVer2 {
    private DNode top;
    private DNode tail;

    public DLinkListFirstVer2() {
        top = null;
        tail = null;
    }

    public void addfirst (String data) {
        if (top == null) {
            top = tail = new DNode(data,null,null);
            return;
        }
        top = new DNode(data, top, null);
        top.getNext().setPrev(top);
    }

    public String DeleteAtEnd () {
        if (top == null)
            return null;

        String temp = top.getData();
        if (top == tail) {
            top = tail = null;
            return temp;
        }

        tail = tail.getPrev();
        tail.setNext(null);

        return temp;
    }

    public void insertInOrder (String data) {
        // no list
        if (top == null)
            top = tail = new DNode(data, null, null);
        else {
            // I have a list
            // insert before top...
            if (top.getData().compareTo(data) >= 1) {
                top = new DNode(data, top, null);
                top.getNext().setPrev(top);
            }

            DNode temp2 = top;
            while (temp2 != null) {
                if (temp2.getData().compareTo(data) >= 1) {
                    DNode temp3 = new DNode(data, temp2, temp2.getPrev());
                    temp3.getPrev().setNext(temp3);
                    temp2.setPrev(temp3);
                    return;
                }
                temp2 = temp2.getNext();
            }

            // insert after tail;
            tail.setNext(new DNode(data, null, tail));
            tail = tail.getNext();
    }
}

    public void moveCurrentDown() {

    }

    public void display() {
        DNode temp = top;

        while (temp != null) {
            System.out.println (temp.getData());
            temp = temp.getNext();
        }
    }

    public int size() {

        int count = 0;
        DNode temp = top;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }


    public static void main (String[] args){
        DLinkListFirstVer2 list = new DLinkListFirstVer2();

        list.insertInOrder("pizza1");
        list.insertInOrder("pizza2");
        list.insertInOrder("pizza5");
        list.insertInOrder("pizza3");
        list.insertInOrder("pizza1");
        list.insertInOrder("pizza5");
        list.insertInOrder("pizza9");

        //list.addToEnd("pizza4");

        list.display();
    }


}

/*

if (top == null)
            top = tail = new DNode(data, null,  null);
        else {
            // I have a list and insert before top
            if(top.getData().compareTo(data) >= 1) {
                top = new DNode(data, top, null);
                top.getNext().setPrev(top);
                return;
            }

            DNode temp = top;
            while (temp.getNext()!= null) {
                if (temp.getData().compareTo(data) >= 1) {
                    DNode temp2 = new DNode(data, temp, temp.getPrev());




            }



        }

 */
