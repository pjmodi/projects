public function preOrder(Node root) {
	if (root == null) {
		return null;
	}

	root.printValue();
	preOrder(root.getLeft());
	preOrder(root.getRight());
}


public function postOrder(Node root) {
	if (root == null) {
		return null;
	}

	postOrder(root.getLeft());
	postOrder(root.getRight());
	root.printValue();
}


public function inOrder(Node root) {
	if (root == null) {
		return null;
	}

	inOrder(root.getLeft());
	root.printValue();
	inOrder(root.getRight());
}


// No recursion
public function preOrderBST(Node root) {

}