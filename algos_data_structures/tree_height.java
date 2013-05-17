// Tree height

public static int heightTree(Node root) {
	if (root == null) {
		return 0;
	}

	return 1 + Math.max(
		heightTree( root.getRight() ), 
		heightTree( root.getLeft() ) 
	);
}