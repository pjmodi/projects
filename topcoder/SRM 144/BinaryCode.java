public class BinaryCode {
	public String[] decode(String message) {
		return new String[0];
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(message, expected, cs);
				break;

			case 0: {
				String message = "123210122";
				String expected[] = new String[] {
					"011100011",
					"NONE"
				};
				doTest(message, expected, cs);
				break;
			}
			case 1: {
				String message = "11";
				String expected[] = new String[] {
					"01",
					"10"
				};
				doTest(message, expected, cs);
				break;
			}
			case 2: {
				String message = "22111";
				String expected[] = new String[] {
					"NONE",
					"11001"
				};
				doTest(message, expected, cs);
				break;
			}
			case 3: {
				String message = "123210120";
				String expected[] = new String[] {
					"NONE",
					"NONE"
				};
				doTest(message, expected, cs);
				break;
			}
			case 4: {
				String message = "3";
				String expected[] = new String[] {
					"NONE",
					"NONE"
				};
				doTest(message, expected, cs);
				break;
			}
			case 5: {
				String message = "12221112222221112221111111112221111";
				String expected[] = new String[] {
					"01101001101101001101001001001101001",
					"10110010110110010110010010010110010"
				};
				doTest(message, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(String message, String[] expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		BinaryCode instance = new BinaryCode();
		String[] result = new String[0];
		try {
			result = instance.decode(message);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		nAll++;
		System.err.print(String.format("  Testcase #%d ... ", caseNo));

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
		}
		else if (equals(result, expected)) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			nPassed++;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + toString(expected));
			System.err.println("           Received: " + toString(result));
		}
	}

	static int nExample = 6;
	static int nAll = 0, nPassed = 0;

	static boolean equals(String[] a, String[] b) {
		if (a.length != b.length) return false;
		for (int i = 0; i < a.length; ++i) if (a[i] == null || b[i] == null || !a[i].equals(b[i])) return false;
		return true;
	}

	static String toString(String[] arr) {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for (int i = 0; i < arr.length; ++i) {
			if (i > 0) sb.append(", ");
			sb.append(arr[i]);
		}
		return sb.toString() + " ]";
	}
	public static void main(String[] args){
		System.err.println("BinaryCode (550 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370323053;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 550 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
