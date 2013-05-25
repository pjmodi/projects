import java.util.*;

class MergeSort extends Sort {
	public MergeSort() {
		System.out.println("Merge Sort");
	}

	public ArrayList<Integer> sort(ArrayList<Integer> input) {
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
		sort(left);
		sort(right);
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
}