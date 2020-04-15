package project3;

import java.io.Serializable;

public class Node implements Serializable {
		private DVD data;
		private Node next;

		public Node(DVD data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public Node() {

		}

		public DVD getData() {
			return data;
		}

		public void setData(DVD data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
}
