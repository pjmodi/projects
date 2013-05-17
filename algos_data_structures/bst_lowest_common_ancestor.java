public function findCommonAncestor(Node root, int v1, int v2) {
	if (root == null) {
		return null;
	}

	int currVal = root.getValue();

	if (currVal < v1 && currVal < v2) {
		return findCommonAncestor(root.getRight(), v1, v2);
	} else if (currVal > v1 && currVal > v2) {
		return findCommonAncestor(root.getLeft(), v1, v2);
	} else if ((currVal > v1 && currVal < v2) || (currVal > v2 && currVal < v1)) {
		return root;
	}
}