// Given an array of numbers, return array of products of all other numbers

class ExclusiveProduct {
	public static int[] findExclusiveProduct(int[] input) {
		
		printResult("Input", input);

		int countZeros = 0;
		int superProduct = 1;

		// Calculate Product of all elements
		for(int i=0; i<input.length; i++) {
			if (input[i] == 0) {
				countZeros++;
			}

			if (countZeros > 1) {
				return replaceWithZeros(input);
			} else if (countZeros <= 1 && input[i] == 0) {
				continue;
			} else {
				superProduct *= input[i];
			}	
		}

		for(int i=0; i<input.length; i++) { 
			if (input[i] == 0) {
				input[i] = superProduct;
			} else if (countZeros > 0 && input[i] != 0) {
				input[i] = 0;
			} else {
				input[i] = superProduct / input[i];
			}
		}

		return input;
	}

	private static int[] replaceWithZeros(int[] input) {
		for(int i=0; i<input.length; i++) {
			input[i] = 0;
		}

		return input;
	}

	private static void printResult(String tag, int[] result) {
		System.out.print(tag + " - ");

		for(int i=0; i<result.length; i++) {
			System.out.print(result[i] + " ");
		}

		System.out.println();
	}

	public static void main(String args[]) {
		int[] result = findExclusiveProduct(new int[]{1, 2, 3});
		printResult("Output", result);

		result = findExclusiveProduct(new int[]{0, 1, 2});
		printResult("Output", result);

		result = findExclusiveProduct(new int[]{0, 0, 0});
		printResult("Output", result);

		result = findExclusiveProduct(new int[]{0, 0, 1});
		printResult("Output", result);

		result = findExclusiveProduct(new int[]{2, 0, 0});
		printResult("Output", result);
	}
}