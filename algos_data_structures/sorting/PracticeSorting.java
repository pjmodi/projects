import java.util.*;

class PracticeSorting {
	public static void main(String[] args) {
		try {
			ArrayList<Integer> values = new ArrayList<Integer>(
				Arrays.asList(84, 54, 85, 59, 31, 3, 63, 99, 86, 87)
			);

			Sorting s = new Sorting();
			// s.SelectionSort(values);
			// s.BubbleSort(values);
			s.InsertionSort(values);
			

		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Do something
		}
	}
}