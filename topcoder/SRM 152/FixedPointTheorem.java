public class FixedPointTheorem {
	public double cycleRange(double R) {
		double x = 0.25;
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;

		for(int i=0; i<201000; i++) {
			x = funcF(R, x);

			if (i>=200000) {
				if (x<min) {
					min = x;
				}

				if(x>max) {
					max = x;
				}
			}
		}

		return max-min;
	}

	public double funcF(double R, double X) {
		return R * X * (1-X);
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(R, expected, cs);
				break;

			case 0: {
				double R = 0.1;
				double expected = 0.0;
				doTest(R, expected, cs);
				break;
			}
			case 1: {
				double R = 3.05;
				double expected = 0.14754098360655865;
				doTest(R, expected, cs);
				break;
			}
			case 2: {
				double R = 3.4499;
				double expected = 0.4175631735867292;
				doTest(R, expected, cs);
				break;
			}
			case 3: {
				double R = 3.55;
				double expected = 0.5325704489850351;
				doTest(R, expected, cs);
				break;
			}
			case 4: {
				double R = 3.565;
				double expected = 0.5454276003030636;
				doTest(R, expected, cs);
				break;
			}
			case 5: {
				double R = 3.5689;
				double expected = 0.5489996297493569;
				doTest(R, expected, cs);
				break;
			}
			case 6: {
				double R = 3.00005;
				double expected = 0.004713996108955176;
				doTest(R, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(double R, double expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		FixedPointTheorem instance = new FixedPointTheorem();
		double result = 0.0;
		try {
			result = instance.cycleRange(R);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		nAll++;
		System.err.print(String.format("  Testcase #%d ... ", caseNo));

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
		}
		else if (doubleEquals(expected, result)) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			nPassed++;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + expected);
			System.err.println("           Received: " + result);
		}
	}

	static int nExample = 7;
	static int nAll = 0, nPassed = 0;

	static boolean doubleEquals(double a, double b) {
		return Math.abs(a - b) < 1e-9 || Math.abs(a) > Math.abs(b) * (1.0 - 1e-9) && Math.abs(a) < Math.abs(b) * (1.0 + 1e-9);
	}

	public static void main(String[] args){
		System.err.println("FixedPointTheorem (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370642216;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
