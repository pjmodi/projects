public class Time {
	public String whatTime(int seconds) {
		int hours = seconds / 3600;
		int mins = (seconds - (hours * 3600)) / 60;
		int secs = seconds - (hours * 3600) - (mins * 60);

		return hours + ":" + mins + ":" + secs;
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(seconds, expected, cs);
				break;

			case 0: {
				int seconds = 0;
				String expected = "0:0:0";
				doTest(seconds, expected, cs);
				break;
			}
			case 1: {
				int seconds = 3661;
				String expected = "1:1:1";
				doTest(seconds, expected, cs);
				break;
			}
			case 2: {
				int seconds = 5436;
				String expected = "1:30:36";
				doTest(seconds, expected, cs);
				break;
			}
			case 3: {
				int seconds = 86399;
				String expected = "23:59:59";
				doTest(seconds, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(int seconds, String expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		Time instance = new Time();
		String result = "";
		try {
			result = instance.whatTime(seconds);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		nAll++;
		System.err.print(String.format("  Testcase #%d ... ", caseNo));

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
		}
		else if (expected.equals(result)) {
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
		System.err.println("Time (200 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370317942;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 200 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
