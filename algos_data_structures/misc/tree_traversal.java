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
	if (root == null) {
		return null;
	}

	NodeStack s = new NodeStack();
	s.push(root);

	while (s.size() > 0) {
		Node n = s.pop();
		n.printValue();

		if (n.getRight() != null) {
			s.push(n.getRight());
		}

		if (n.getLeft() != null) {
			s.push(n.getLeft());
		}
	}
}