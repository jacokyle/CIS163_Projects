package project3.LinkedListClassNotes;

public class DLinkListFirst{
	private DNode top;
	private DNode tail;

	public DLinkListFirst(){
		top = null;
		tail = null;
	}

	public void addfirst(String data){

		// case 1: no list
		if (top == null){
			tail = top = new DNode(data, null, null);
			return;
		}

		// case 2: is a list
		else{
			top = new DNode (data, top, null);
			top.getNext().setPrev(top);

//			DNode temp = new DNode();
//			temp.setData(data);
//			temp.setPrev(null);
//			temp.setNext(top);
//			top.setPrev(temp);
//			top = temp;
		}
	}

	public String DeleteAtEnd(){

		// no list
		if (top == null)
			return null;
		
		// one item in list
		if (top == tail) {
			String temp = top.getData();
			top = tail = null;
			return temp;
		}
		
		// multi items
		String temp = tail.getData();
		tail = tail.getPrev();
		tail.setNext(null);
		return temp;		
	}

	public void insertAfterCurrent(String data){

	}

	public void moveCurrentDown(){

	}

	public void display(){
		DNode temp = top;
		int lineNumber = 0;

		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}

	public int count(){
		return 0;
	}


	public static void main(String[] args){
		DLinkListFirst list = new DLinkListFirst();

		list.addfirst("pizza1");
		list.addfirst("pizza2");
		list.moveCurrentDown();
		list.insertAfterCurrent("pizza5");
		list.addfirst("pizza3");
		//list.addToEnd("pizza4");	

		list.display();
	}
}
