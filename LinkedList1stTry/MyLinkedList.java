package LinkedList1stTry;

public class MyLinkedList {
	Node head;
	Node tail;
	int size = 0;
	
	public Node getNodeFromIndex (int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		if (index < size/2) {
			Node temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			return temp;
		} else {
			Node temp = tail;
			for (int i = size; i > index; i--) {
				temp = temp.prev;
			}
			return temp;
		}
	}
	
	public Node getNodeFromVal (int value) {
		Node temp = head;
		for (int i = 0; i < size; i++) {
			if (temp.value == value) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	public void addNode(Node newNode, int index) {
	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException();
	    }
	    
	    if (index == 0) {
	        newNode.next = head;
	        if (head != null) {
	            head.prev = newNode;
	        } else {
	            tail = newNode;
	        }
	        head = newNode;
	    } else if (index == size) {
	        tail.next = newNode;
	        newNode.prev = tail;
	        tail = newNode;
	    } else {
	        Node temp = head;
	        for (int i = 0; i < index; i++) {
	            temp = temp.next;
	        }
	        Node previous = temp.prev;
	        previous.next = newNode;
	        newNode.prev = previous;
	        newNode.next = temp;
	        temp.prev = newNode;
	    }
	    size++;
	}
}
