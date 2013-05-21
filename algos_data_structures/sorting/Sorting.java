import java.util.*;

class Sorting {
	public Sorting() {

	}

	public ArrayList<Integer> BubbleSort(ArrayList<Integer> input) {
		return null;
	}

	public ArrayList<Integer> InsertionSort(ArrayList<Integer> input) {
		return null;
	}

	public ArrayList<Integer> MergeSort(ArrayList<Integer> input) {
		return null;
	}

	public ArrayList<Integer> QuickSort(ArrayList<Integer> input) {
		return null;
	}

	public ArrayList<Integer> SelectionSort(ArrayList<Integer> input) {
		int minIndex = 0;

		for(int i=0; i<input.size(); i++) {
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
		// System.out.println("Swap index " + i + " & " + j);

		if (i!=j) {
			int temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}
}