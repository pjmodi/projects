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
		return null;
	}

	public ArrayList<Integer> QuickSort(ArrayList<Integer> input) {
		return null;
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