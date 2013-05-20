import java.util.*;

class BTree {
	BTNode root = null;

	public BTree(ArrayList<Integer> values) {
		for(int item : values) {
			if (root == null) {
				root = new BTNode(null, item);
				System.out.println("Set root of tree " + item);
			} else {
				BTNode node = new BTNode(item);
				insertIntoTree(node);
			}
		}
	}

	public BTNode getRoot() {
		return root;
	}

	public void insertIntoTree(BTNode n) {
		if (root == null) {
			root = n;
			return;
		}

		BTNode parentNode = root;
		boolean foundParent = false;

		// Find the appropriate parent for this node
		while(!foundParent) {
			if (n.getValue() <= parentNode.getValue()) {
				if (parentNode.getLeftChild() == null) {
					foundParent = true;
					System.out.println("Set left child of " + parentNode.getValue() + " to " + n.getValue());
					parentNode.setLeftChild(n);
					n.setParent(parentNode);
				} else {
					parentNode = parentNode.getLeftChild();
				}
			} else {
				if (parentNode.getRightChild() == null) {
					foundParent = true;
					System.out.println("Set right child of " + parentNode.getValue() + " to " + n.getValue());
					parentNode.setRightChild(n);
					n.setParent(parentNode);
				} else {
					parentNode = parentNode.getRightChild();
				}
			}
		}
	}

	// Number of nodes in the tree
	public int getSize(BTNode root) {
		if (root == null) {
			return 0;
		} else {
			return ( getSize(root.getLeftChild()) + 1 + getSize(root.getRightChild()) );
		}
	} 

	// Height of the tree
	public int getHeight(BTNode root) {
		if (root == null) {
			return 0;
		}

		return 1 + Math.max(
			getHeight( root.getRightChild() ), 
			getHeight( root.getLeftChild() ) 
		);
	}

	public BTNode findCommonAncestor(BTNode root, int v1, int v2) {
		if (root == null) {
			return null;
		}

		int currVal = root.getValue();

		if (currVal < v1 && currVal < v2) {
			return findCommonAncestor(root.getRightChild(), v1, v2);
		} else if (currVal > v1 && currVal > v2) {
			return findCommonAncestor(root.getLeftChild(), v1, v2);
		} else {
			return root;
		}
	}

	public void preOrder(BTNode root) {
		if (root == null) {
			return;
		}

		System.out.print(root.getValue() + " ");
		preOrder(root.getLeftChild());
		preOrder(root.getRightChild());
	}

	// No recursion
	// public function preOrder_noRecursion(Node root) {
	// 	if (root == null) {
	// 		return null;
	// 	}

	// 	NodeStack s = new NodeStack();
	// 	s.push(root);

	// 	while (s.size() > 0) {
	// 		Node n = s.pop();
	// 		n.printValue();

	// 		if (n.getRight() != null) {
	// 			s.push(n.getRight());
	// 		}

	// 		if (n.getLeft() != null) {
	// 			s.push(n.getLeft());
	// 		}
	// 	}
	// }


	public void postOrder(BTNode root) {
		if (root == null) {
			return;
		}

		postOrder(root.getLeftChild());
		postOrder(root.getRightChild());
		System.out.print(root.getValue() + " ");
	}


	public void inOrder(BTNode root) {
		if (root == null) {
			return;
		}

		inOrder(root.getLeftChild());
		System.out.print(root.getValue() + " ");
		inOrder(root.getRightChild());
	}

	// In a BST, the smallest value is the left lower-most child
	public int minValue() {
		BTNode currentNode = this.root;

		while (currentNode.getLeftChild() != null) {
			currentNode = currentNode.getLeftChild();
		}

		return currentNode.getValue();
	}

	// In a BST, the greatest value is the right lower-most child
	public int maxValue() {
		BTNode currentNode = this.root;
		
		while (currentNode.getRightChild() != null) {
			currentNode = currentNode.getRightChild();
		}

		return currentNode.getValue();
	}

	public BTNode findBTNode(BTNode root, int value) {
		if (root == null) {
			return null;
		}

		int currValue = root.getValue();

		if (currValue == value) {
			return root;
		} else if (value < currValue) {
			return findBTNode(root.getLeftChild(), value);
		} else if (value > currValue) {
			return findBTNode(root.getRightChild(), value);
		}

		return null;
	}

	public BTNode breadthFirstSearch(BTNode root, int value) {
		if (root == null) {
			return null;
		}

		Queue<BTNode> queue = new LinkedList<BTNode>();
		queue.add(root);

		while(queue.size() > 0) {
			BTNode currentBTNode = queue.remove();
			System.out.print(currentBTNode.getValue() + " ");

			if (currentBTNode.getValue() == value) {
				return currentBTNode;
			} else {
				// Add children
				if (currentBTNode.getLeftChild() != null) {
					queue.add(currentBTNode.getLeftChild());
				}
				if (currentBTNode.getRightChild() != null) {
					queue.add(currentBTNode.getRightChild());
				}
			}
		}

		return null;
	}

	public BTNode depthFirstSearch(BTNode root, int value) {
		if (root == null) {
			return null;
		}

		Stack<BTNode> stack = new Stack<BTNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			BTNode currentBTNode = stack.pop();
			System.out.print(currentBTNode.getValue() + " ");

			if (currentBTNode.getValue() == value) {
				return currentBTNode;
			} else {
				if (currentBTNode.getRightChild() != null) {
					stack.push(currentBTNode.getRightChild());
				}
				if (currentBTNode.getLeftChild() != null) {
					stack.push(currentBTNode.getLeftChild());
				}
			}
		}

		return null;
	}

}