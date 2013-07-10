// Pseudo-code

class Node {
	Node next;
	int value;	
}

class LinkedListSwap {
	Node root;

	public LinkedListSwap() {
		// Initializes your
	}

	public void swapList() {
		// Get mid-point for list
		int count = getCountOfList(this.root);
		int mid = Math.floor(count / 2);

		// Split the list into two sets, second set should be reversed
		Node firstSet = this.root;
		Node secondSet = this.root;
		for(int i=0; i<=mid; i++) {
			secondSet = secondSet.next;
		}
		reverseListStartingWith(secondSet);

		// Insert element from the second set into the first set at every odd index.
		int i = 1;
		while(secondSet.next != null) {
			Node temp = secondSet;
			secondSet = secondSet.next;
			insertNodeAtIndex(temp, i);
			i += 2;
		}
	}

	// Parse through the list and insert given node at specd. index
	private void insertNodeAtIndex(Node n, int index) {
		Node current = this.root;
		int i = 0;
		while(i<index) {
			current = current.next;
		}

		Node temp = current.next;
		current.next = n;
		n.next = temp;
	}

	// Get total count of elements inside specd. list
	private int getCountOfList(Node root) {
		if (root == null) {
			return 0;
		} else if (root.next == null) {
			return 1;
		}

		int count = 1;
		Node current = root;

		while(current.next != null) {
			count++;
			current = current.next;
		}

		return count;
	}

	// Reverse elements in a singly linked list
	private void reverseListStartingWith(Node root) {
		if (root == null) {
			return;
		}

		Node current = root;
		Node next = root.next;
		Node remainder = null;
		root.next = null;

		while(next != null) {
			remainder = next.next;
			next.next = current;
			current = next;
			next = remainder;
		}

		root = current;
	}
}