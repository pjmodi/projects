public class MostProfitable {
	public String bestItem(int[] costs, int[] prices, int[] sales, String[] items) {
		int max = 0;
		int maxIndex = -1;

		for(int i=0; i<items.length; i++) {
			int profit = (prices[i] * sales[i]) - (costs[i] * sales[i]);
			if (profit > 0 && profit > max) {
				max = profit;
				maxIndex = i;
			}
		}

		if (maxIndex == -1) {
			return "";
		}

		return items[maxIndex];
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(costs, prices, sales, items, expected, cs);
				break;

			case 0: {
				int costs[] = new int[] {
					100, 120, 150, 1000
				};
				int prices[] = new int[] {
					110, 110, 200, 2000
				};
				int sales[] = new int[] {
					20, 100, 50, 3
				};
				String items[] = new String[] {
					"Video Card",
					"256M Mem",
					"CPU/Mobo combo",
					"Complete machine"
				};
				String expected = "Complete machine";
				doTest(costs, prices, sales, items, expected, cs);
				break;
			}
			case 1: {
				int costs[] = new int[] {
					100
				};
				int prices[] = new int[] {
					100
				};
				int sales[] = new int[] {
					134
				};
				String items[] = new String[] {
					"Service, at cost"
				};
				String expected = "";
				doTest(costs, prices, sales, items, expected, cs);
				break;
			}
			case 2: {
				int costs[] = new int[] {
					38, 24
				};
				int prices[] = new int[] {
					37, 23
				};
				int sales[] = new int[] {
					1000, 643
				};
				String items[] = new String[] {
					"Letter",
					"Postcard"
				};
				String expected = "";
				doTest(costs, prices, sales, items, expected, cs);
				break;
			}
			case 3: {
				int costs[] = new int[] {
					10, 10
				};
				int prices[] = new int[] {
					11, 12
				};
				int sales[] = new int[] {
					2, 1
				};
				String items[] = new String[] {
					"A",
					"B"
				};
				String expected = "A";
				doTest(costs, prices, sales, items, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(int[] costs, int[] prices, int[] sales, String[] items, String expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		MostProfitable instance = new MostProfitable();
		String result = "";
		try {
			result = instance.bestItem(costs, prices, sales, items);
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
		System.err.println("MostProfitable (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370643349;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
