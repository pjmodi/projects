public class DivisorDigits {
	public int howMany(int number) {
		int temp = number; 
		int lastDigit = 0; 
		int count = 0;
		do {
			lastDigit = temp % 10;
			temp = temp / 10;

			if (lastDigit == 0) {
				continue;
			}

			if (number % lastDigit == 0) {
				count++;
			}
		} while(temp != 0);

		return count;
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(number, expected, cs);
				break;

			case 0: {
				int number = 12345;
				int expected = 3;
				doTest(number, expected, cs);
				break;
			}
			case 1: {
				int number = 661232;
				int expected = 3;
				doTest(number, expected, cs);
				break;
			}
			case 2: {
				int number = 52527;
				int expected = 0;
				doTest(number, expected, cs);
				break;
			}
			case 3: {
				int number = 730000000;
				int expected = 0;
				doTest(number, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(int number, int expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		DivisorDigits instance = new DivisorDigits();
		int result = 0;
		try {
			result = instance.howMany(number);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		nAll++;
		System.err.print(String.format("  Testcase #%d ... ", caseNo));

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
		}
		else if (result == expected) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			nPassed++;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + expected);
			System.err.println("           Received: " + result);
		}
	}

	static int nExample = 4;
	static int nAll = 0, nPassed = 0;

	public static void main(String[] args){
		System.err.println("DivisorDigits (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370383047;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
