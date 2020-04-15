package project3.LinkedListClassNotes2;
public class DNode {
		private String data;
		private DNode next;
		private DNode prev;

		public DNode(String data, DNode next, DNode prev) {
			super();
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		public DNode() {
			super();
		}

		public String getData() {
			return data;
		}
		
		public void setData(String data) {
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
