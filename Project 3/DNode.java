package project3;

        import java.io.Serializable;

public class DNode implements Serializable {
    private DVD data;
    private DNode next;
    private DNode prev;

    public DNode(DVD data, DNode next, DNode prev) {
        super();
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public DNode() {

    }

    public DVD getData() {
        return data;
    }

    public void setData(DVD data) {
        this.data = data;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }
}
