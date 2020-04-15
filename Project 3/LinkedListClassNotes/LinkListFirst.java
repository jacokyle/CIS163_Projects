package project3.LinkedListClassNotes;


public class LinkListFirst {
    private Node top;
    private Node tail;


    public LinkListFirst() {
        top = null;
        tail = null;
    }

    public boolean removeFromTop() {
        return true;
    }

    public String removeFromBottom() {
        // no list
        if (top == null)
            return null;

        // one item in the list
        if (top == tail) {
            String temp = top.getData();
            top = tail = null;
            return temp;
        }

        // multi items in list not at top.
        Node temp = top;
        while (temp.getNext().getNext() != null)
            temp = temp.getNext();

        String data = tail.getData();
        temp.setNext(null);
        tail = temp;
        return data;
    }

    public void addfirst(String data) {

        // no list
        if (top == null)
            tail = top = new Node(data, top);
        else
            // there is a list
            top = new Node(data, top);

//		Node temp = new Node();
//		temp.setData(data);
//		temp.setNext(top);
//		top = temp;
    }

    public void addToBottom(String data) {
        // no list
        if (top == null)
            tail = top = new Node(data, top);
        else {
            tail.setNext(new Node(data, null));
            tail = tail.getNext();
        }
    }

    public void display() {
//		System.out.println(top.getData());
//		System.out.println(top.getNext().getData());
//		System.out.println(top.getNext().getNext().getData());
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    public String remove(int index) {
        return null;
    }

    public static void main(String[] args) {
        LinkListFirst list = new LinkListFirst();

        list.addfirst("pizza1");
        list.addfirst("pizza2");
        list.addfirst("pizza3");
        list.addfirst("pizza4");
        list.addfirst("pizza5");
        list.addfirst("pizza6");
        list.addfirst("pizza7");
        list.addfirst("pizza8");

        list.removeFromTop();
        list.removeFromTop();

        list.display();
    }
}