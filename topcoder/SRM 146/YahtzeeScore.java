import java.util.*;

public class YahtzeeScore {
	public int maxPoints(int[] toss) {
		Map<Integer, Integer> numbers = new HashMap<Integer, Integer>();
		for(int roll : toss) {
			if (!numbers.containsKey(roll)) {
				numbers.put(roll, 0);
			}
			int existing = (int) numbers.get(roll);
			numbers.put(roll, existing + 1);
		}

		int max = Integer.MIN_VALUE;

		for(Map.Entry<Integer, Integer> entry : numbers.entrySet())	{
			int value = entry.getKey() * entry.getValue();
			if (value > max) {
				max = value;
			}
		}	

		return max;
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(toss, expected, cs);
				break;

			case 0: {
				int toss[] = new int[] {
					2, 2, 3, 5, 4
				};
				int expected = 5;
				doTest(toss, expected, cs);
				break;
			}
			case 1: {
				int toss[] = new int[] {
					6, 4, 1, 1, 3
				};
				int expected = 6;
				doTest(toss, expected, cs);
				break;
			}
			case 2: {
				int toss[] = new int[] {
					5, 3, 5, 3, 3
				};
				int expected = 10;
				doTest(toss, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(int[] toss, int expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		YahtzeeScore instance = new YahtzeeScore();
		int result = 0;
		try {
			result = instance.maxPoints(toss);
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

	static int nExample = 3;
	static int nAll = 0, nPassed = 0;

	public static void main(String[] args){
		System.err.println("YahtzeeScore (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370325299;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
