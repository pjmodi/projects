import java.util.*;

class BubbleSort extends Sort {
	public BubbleSort() {
		System.out.println("Bubble Sort");
	}

	// Bubble up the highest value on every cycle
	public ArrayList<Integer> sort(ArrayList<Integer> input) {
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
}