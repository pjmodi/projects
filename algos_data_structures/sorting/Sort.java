import java.util.*;

class Sort {
	public Sort() {

	}

	public int findMinimumIndex(ArrayList<Integer> list, int offset) {
		int minIndex = offset;

		for(int i=offset; i<list.size(); i++) {
			if (list.get(i) < list.get(minIndex)) {
				minIndex = i;
			}
		}

		return minIndex;
	}

	public void swap(ArrayList<Integer> list, int i, int j) {
		if (i!=j) {
			int temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}
}