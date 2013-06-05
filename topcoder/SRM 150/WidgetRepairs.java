public class WidgetRepairs {
	public int days(int[] arrivals, int numPerDay) {
		int days = 0;
		int work = 0;

		for(int i=0; i<arrivals.length; i++) {
			work += arrivals[i];
			if (work > 0) {
				days++;
			}

			if (work - numPerDay > 0) {
				work = work - numPerDay;
			} else {
				work = 0;
			}

			// System.out.println(days + " - " + work);
		}

		while(work > 0) {
			days++;
			work -= numPerDay;
		}

		return days;
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(arrivals, numPerDay, expected, cs);
				break;

			case 0: {
				int arrivals[] = new int[] {
					10, 0, 0, 4, 20
				};
				int numPerDay = 8;
				int expected = 6;
				doTest(arrivals, numPerDay, expected, cs);
				break;
			}
			case 1: {
				int arrivals[] = new int[] {
					0, 0, 0
				};
				int numPerDay = 10;
				int expected = 0;
				doTest(arrivals, numPerDay, expected, cs);
				break;
			}
			case 2: {
				int arrivals[] = new int[] {
					100, 100
				};
				int numPerDay = 10;
				int expected = 20;
				doTest(arrivals, numPerDay, expected, cs);
				break;
			}
			case 3: {
				int arrivals[] = new int[] {
					27, 0, 0, 0, 0, 9
				};
				int numPerDay = 9;
				int expected = 4;
				doTest(arrivals, numPerDay, expected, cs);
				break;
			}
			case 4: {
				int arrivals[] = new int[] {
					6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6
				};
				int numPerDay = 3;
				int expected = 15;
				doTest(arrivals, numPerDay, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(int[] arrivals, int numPerDay, int expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		WidgetRepairs instance = new WidgetRepairs();
		int result = 0;
		try {
			result = instance.days(arrivals, numPerDay);
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

	static int nExample = 5;
	static int nAll = 0, nPassed = 0;

	public static void main(String[] args){
		System.err.println("WidgetRepairs (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370385414;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
