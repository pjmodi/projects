import java.util.*;

class InsertionSort extends Sort {
	public InsertionSort() {
		System.out.println("Insertion Sort");
	}

	// Insert the smaller value towards the front on every cycle
	public ArrayList<Integer> sort(ArrayList<Integer> input) {
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
}