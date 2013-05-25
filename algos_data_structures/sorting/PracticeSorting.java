import java.util.*;

class PracticeSorting {
	public static void main(String[] args) {
		try {
			ArrayList<Integer> values = new ArrayList<Integer>(
				Arrays.asList(84, 54, 85, 59, 31, 3, 63, 99, 86, 87)
			);

			// BubbleSort s = new BubbleSort();
			// SelectionSort s = new SelectionSort();
			// InsertionSort s = new InsertionSort();
			// QuickSort s = new QuickSort();
			MergeSort s = new MergeSort();

			System.out.println("Input: " + values);
			ArrayList<Integer> output = s.sort(values);
			System.out.println("Output: " + output);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Do something
		}
	}
}