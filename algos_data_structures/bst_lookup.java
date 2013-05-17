// Lookup in a BST

Node findNode(Node root, int value) {
	if (root == null) {
		return null;
	}

	int currValue = root.getValue();

	if (currValue == value) {
		return root;
	} else if (value < currValue) {
		return findNode(root.getLeft(), value);
	} else if (value > currValue) {
		return findNode(root.getRight(), value);
	}
}