class BTNode {
	private BTNode parent = null;	
	private BTNode left = null;
	private BTNode right = null;
	private int value;

	public BTNode(BTNode parent, int value) {
		this.parent = parent;
		this.value = value;
	}

	public BTNode(int value) {
		this.value = value;
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public void setParent(BTNode parent) {
		this.parent = parent;
	}

	public BTNode getParent() {
		return this.parent;
	}

	public void setLeftChild(BTNode left) {
		this.left = left;
	}

	public BTNode getLeftChild() {
		return this.left;
	}

	public void setRightChild(BTNode right) {
		this.right = right;
	}

	public BTNode getRightChild() {
		return this.right;
	}

	public int getValue() {
		return this.value;
	}
}