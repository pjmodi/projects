public class ProfitCalculator {
	public int percent(String[] items) {
		double cost = 0.0, price = 0.0;

		for(String item : items) {
			String points[] = item.split("\\s+");
			price += Double.parseDouble(points[0]);
			cost += Double.parseDouble(points[1]);
		}

		double percent = Math.floor(((price - cost)  / price) * 100);
		return (int) percent;
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(items, expected, cs);
				break;

			case 0: {
				String items[] = new String[] {
					"012.99 008.73",
					"099.99 050.00",
					"123.45 101.07"
				};
				int expected = 32;
				doTest(items, expected, cs);
				break;
			}
			case 1: {
				String items[] = new String[] {
					"000.00 049.99",
					"999.99 936.22",
					"033.99 025.64",
					"249.99 211.87"
				};
				int expected = 4;
				doTest(items, expected, cs);
				break;
			}
			case 2: {
				String items[] = new String[] {
					"822.77 704.86",
					"829.42 355.45",
					"887.18 949.38"
				};
				int expected = 20;
				doTest(items, expected, cs);
				break;
			}
			case 3: {
				String items[] = new String[] {
					"612.72 941.34",
					"576.46 182.66",
					"787.41 524.70",
					"637.96 333.23",
					"345.01 219.69",
					"567.22 104.77",
					"673.02 885.77"
				};
				int expected = 23;
				doTest(items, expected, cs);
				break;
			}
			case 4: {
				String items[] = new String[] {
					"811.22 275.32",
					"433.89 006.48",
					"141.28 967.41",
					"344.47 786.23",
					"897.47 860.61",
					"007.42 559.29",
					"255.72 460.00",
					"419.35 931.19",
					"419.25 490.52",
					"199.78 114.44",
					"505.63 276.58",
					"720.96 735.00",
					"719.90 824.46",
					"816.58 195.94",
					"498.68 453.05",
					"399.48 921.39",
					"930.88 017.63",
					"422.20 075.39",
					"380.22 917.27",
					"630.83 995.87",
					"821.07 126.87",
					"715.73 985.62",
					"246.23 134.64",
					"168.28 550.33",
					"707.28 046.72",
					"117.76 281.87",
					"595.43 410.45",
					"345.28 532.42",
					"554.24 264.34",
					"195.73 814.87",
					"131.98 041.28",
					"595.13 939.47",
					"576.04 107.70",
					"716.00 404.75",
					"524.24 029.96",
					"673.49 070.97",
					"288.09 849.43",
					"616.34 236.34",
					"401.96 316.33",
					"371.18 014.27",
					"809.63 508.33",
					"375.68 290.84",
					"334.66 477.89",
					"689.54 526.35",
					"084.77 316.51",
					"304.76 015.91",
					"460.63 636.56",
					"357.84 436.20",
					"752.24 047.64",
					"922.10 573.12"
				};
				int expected = 10;
				doTest(items, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(String[] items, int expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		ProfitCalculator instance = new ProfitCalculator();
		int result = 0;
		try {
			result = instance.percent(items);
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
		System.err.println("ProfitCalculator (300 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370659056;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 300 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
