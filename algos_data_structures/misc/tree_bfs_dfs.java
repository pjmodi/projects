public BTNode breadthFirstSearch(BTNode root, int value) {
	if (root == null) {
		return false;
	}

	Queue<BTNode> queue = new LinkedList<BTNode>();
	queue.add(root);

	while(queue.size() > 0) {
		BTNode currentBTNode = queue.remove();
		if (currentBTNode.getValue() == value) {
			return currentBTNode;
		}
		
		// Queue all the children of the node
		foreach(BTNode child : currentBTNode.getChildren()) {
			queue.add(child);
		}
	}

	return false;
}


public BTNode depthFirstSearch(BTNode root, int value) {
	if (root == null) {
		return false;
	}

	Queue<BTNode> queue = new LinkedList<BTNode>();
	queue.add(root);

	while (queue.size() > 0) {
		BTNode currentBTNode = queue.remove();
		if (currentBTNode.getValue() == value) {
			return currentBTNode;
		}

		// Children come in left-to-right order
		foreach(BTNode child : currentBTNode.getChildren()) {
			if (!child.isVisited) {
				child.setVisited(true);
				queue.add(child);
				break;
			}
		}
	}

	return false;
}