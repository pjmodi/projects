import java.util.*;

class QuickSort extends Sort {
	public QuickSort() {
		System.out.println("Quick Sort");
	}

	public ArrayList<Integer> sort(ArrayList<Integer> input) {
		if (input.size() < 2) {
			return input;
		}

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();

		// Randomly pick a pivot in the middle
		int pivotIndex = input.size() / 2;
		int pivotValue = input.get(pivotIndex);
		// System.out.println("Pivot - " + pivotValue);

		for (int i=0; i<input.size(); i++) {
			// Skip over the pivot itself
			if (i == pivotIndex) {
				continue;
			}

			int val = input.get(i);
			if (val < pivotValue) {
				left.add(val);
			} else {
				right.add(val);
			}
		}

		// Sort the subsets
		System.out.println(left + " - " + pivotValue + " - " + right);
		left = sort(left);
		right = sort(right);
		
		// System.out.println(left + " - " + pivotValue + " - " + right);
		input = new ArrayList<Integer>(left);
		input.add(pivotValue);
		input.addAll(right);

		return input;
	}
}