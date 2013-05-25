import java.util.*;

class SelectionSort extends Sort {
	public SelectionSort() {
		System.out.println("Selection Sort");
	}

	// Swap the first element with the smallest element from 2-n and so on.
	public ArrayList<Integer> sort(ArrayList<Integer> input) {
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
}