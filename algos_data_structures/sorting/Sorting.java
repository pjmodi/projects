import java.util.*;

class Sorting {
	public Sorting() {

	}

	// Bubble up the highest value on every cycle
	public ArrayList<Integer> BubbleSort(ArrayList<Integer> input) {
		int n = input.size();

		for(int i=0; i<n; i++) {
			for(int j=1; j<(n-i); j++) {
				if (input.get(j-1) > input.get(j)) {
					swap(input, j-1, j);
					System.out.println(input);
				}
			}
		}

		return input;
	}

	// Insert the smaller value towards the front on every cycle
	public ArrayList<Integer> InsertionSort(ArrayList<Integer> input) {
		int n = input.size();

		for(int i=0; i<n; i++) {
			for(int j=i; j>0; j--) {
				if (input.get(j-1) > input.get(j)) {
					swap(input, j-1, j);
					System.out.println(input);
				}
			}
		}

		return input;
	}

	public ArrayList<Integer> MergeSort(ArrayList<Integer> input) {
		if (input.size() < 2) {
			return input;
		}

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		int middle = input.size() / 2;
		
		// Split the array into two
		for(int i=0; i<input.size(); i++) {
			if (i<middle) {
				left.add(input.get(i));
			} else {
				right.add(input.get(i));
			}
		}

		System.out.println(left + " --- " + right);
		MergeSort(left);
		MergeSort(right);
		return Merge(input, left, right);
	}

	private ArrayList<Integer> Merge(ArrayList<Integer> dest, ArrayList<Integer> left, ArrayList<Integer> right) {
		int dind = 0;
		int lind = 0;
		int rind = 0;

		// Go thru both left and right arrays and add the lower value to the destination array
		while(lind < left.size() && rind < right.size()) {
			if (left.get(lind) <= right.get(rind)) {
				dest.set(dind++, left.get(lind++));
			} else {
				dest.set(dind++, right.get(rind++));
			}
		}

		// Copy balance values over (incase left and right are not the same size)
		while(lind < left.size()) {
			dest.set(dind++, left.get(lind++));
		}
		while(rind < right.size()) {
			dest.set(dind++, right.get(rind++));
		}

		return dest;
	}

	public ArrayList<Integer> QuickSort(ArrayList<Integer> input) {
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
		left = QuickSort(left);
		right = QuickSort(right);
		
		// System.out.println(left + " - " + pivotValue + " - " + right);
		input = new ArrayList<Integer>(left);
		input.add(pivotValue);
		input.addAll(right);
		System.out.println(input);

		return input;
	}

	// Swap the first element with the smallest element from 2-n and so on.
	public ArrayList<Integer> SelectionSort(ArrayList<Integer> input) {
		int minIndex = 0;
		int n = input.size();

		for(int i=0; i<n; i++) {
			System.out.println(input);
			minIndex = findMinimumIndex(input, i);
			if (i<minIndex) {
				swap(input, i, minIndex);
			}
		}

		return input;
	}

	private int findMinimumIndex(ArrayList<Integer> list, int offset) {
		int minIndex = offset;

		for(int i=offset; i<list.size(); i++) {
			if (list.get(i) < list.get(minIndex)) {
				minIndex = i;
			}
		}

		return minIndex;
	}

	public static void swap(ArrayList<Integer> list, int i, int j) {
		if (i!=j) {
			int temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}
}