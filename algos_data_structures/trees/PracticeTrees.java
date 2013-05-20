import java.util.*;

class PracticeTrees {
	public static void main(String[] args) {
		try {
			// ArrayList<Integer> values = new ArrayList<Integer>();
			// values.addAll(generateRandomNumbers(10));
			ArrayList<Integer> values = new ArrayList<Integer>(
				Arrays.asList(84, 54, 85, 59, 31, 3, 63, 99, 86, 87)
			);
			
			System.out.println("\nGenerating random values...");
			System.out.println(values);

			System.out.println("\nBuilding BST...");
			BTree tree = new BTree(values);

			System.out.println("\nTotal nodes in Tree...");
			System.out.println(tree.getSize(tree.getRoot()));

			System.out.println("\nHeight of Tree...");
			System.out.println(tree.getHeight(tree.getRoot()));	

			System.out.println("\nPre-order...");
			tree.preOrder(tree.getRoot());

			System.out.println("\n\nPost-order...");
			tree.postOrder(tree.getRoot());

			System.out.println("\n\nIn-order...");
			tree.inOrder(tree.getRoot());

			System.out.println("\n\nCommon ancestor 31 & 99...");
			System.out.println(tree.findCommonAncestor(tree.getRoot(), 31, 99).getValue());

			System.out.println("\nMin value...");
			System.out.println(tree.minValue());

			System.out.println("\nMax value...");
			System.out.println(tree.maxValue());

			System.out.println("\nSearch tree...");
			BTNode find = tree.findBTNode(tree.getRoot(), 31);
			if (find != null) {
				System.out.println("Found " + find.getValue());
			} else {
				System.out.println("Not found.");
			}

			System.out.println("\nBreadth first search for 99...");
			tree.breadthFirstSearch(tree.getRoot(), 99);

			System.out.println("\n\nDepth first search for 99...");
			tree.depthFirstSearch(tree.getRoot(), 99);

			System.out.println("\n\n");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			// Nothing to do right now
		}
	}

	// private static ArrayList<Integer> generateRandomNumbers(int count) {
	// 	ArrayList<Integer> values = new ArrayList<Integer>();

	// 	while(values.size() < count) {
	// 		int number = (int) Math.floor(Math.random() * 100);
			
	// 		if (!values.contains(number)) {
	// 			values.add(number);
	// 		}
	// 	}

	// 	return values;
	// }
}