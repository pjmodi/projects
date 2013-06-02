// Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. 
// C is said to be interleaving A and B, if it contains all characters of A and B and order of all characters in individual strings is preserved.
// A = {X, Y, Z}, B = {1, 2, 3} & C = {X, 1, 2, 3, Y, Z}

// More solutions here:
// http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings/

class Interleaved {

	/**
	MY SOLUTION (INCORRECT)
	**/
	// public static boolean isInterleaved(String a, String b, String c) {
	// 	char[] ca = a.toCharArray();
	// 	char[] cb = b.toCharArray();
	// 	char[] cc = c.toCharArray();

	// 	int la = ca.length;
	// 	int lb = cb.length;
	// 	int lc = cc.length;

	// 	System.out.println("A - " + a);
	// 	System.out.println("B - " + b);
	// 	System.out.println("C - " + c);

	// 	// Check if len(C) = len(A+B)
	// 	if (lc != la + lb) {
	// 		return false;
	// 	}

	// 	// Starting index
	// 	int sa = 0, sb = 0, sc = 0;

	// 	// Loop through C and check in A & B
	// 	for(sc = 0; sc < lc; sc++) {
	// 		System.out.println("Evaluating " + cc[sc]);
	// 		char lookup = cc[sc];

	// 		if (sa<la) {
	// 			char nextA = ca[sa];
	// 			if (lookup==nextA) {
	// 				sa++;
	// 				System.out.println("In A");
	// 				continue;
	// 			}
	// 		}
			
	// 		if (sb<lb) {
	// 			char nextB = cb[sb];
	// 			if (lookup == nextB) {
	// 				sb++;
	// 				System.out.println("In B");
	// 				continue;
	// 			}
	// 		}

	// 		return false;
	// 	}

	// 	return true;
	// }

	
	private static boolean isInterleaving(String a, String b, String c, int i, int j, int k) {
        if (i >= a.length() && j >= b.length() && k >= c.length()) {
            return true;
        }
 
        boolean ret = false;

        if (i < a.length() && a.charAt(i) == c.charAt(k)) {
            ret |= isInterleaving(a, b, c, i + 1, j, k + 1);
        }

        if (!ret && j < b.length() && b.charAt(j) == c.charAt(k)) {
            ret |= isInterleaving(a, b, c, i, j + 1, k + 1);
        }
 
        return ret;
    }
 
    public static boolean isInterleaved(String a, String b, String c) {
        if (a.length() + b.length() != c.length()) {
            return false;
        }
        return isInterleaving(a, b, c, 0, 0, 0);
    }


	private static void printResult(boolean result) {
		if (result) {
			System.out.println("C is interleaved in A & B\n\n");
		} else {
			System.out.println("C is NOT interleaved in A & B\n\n");
		}
	}

	public static void main(String args[]) {
		boolean result = isInterleaved("XYZ", "123", "X123YZ");
		printResult(result);

		result = isInterleaved("XXY", "213", "XXY213");
		printResult(result);

		result = isInterleaved("AAB", "AAC", "AACAAB");
		printResult(result);
	}
}