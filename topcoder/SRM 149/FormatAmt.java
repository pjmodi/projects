import java.util.*;
import java.text.*;

public class FormatAmt {
	public String amount(int dollars, int cents) {
		return String.format("$%,d.%02d", dollars, cents);
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(dollars, cents, expected, cs);
				break;

			case 0: {
				int dollars = 123456;
				int cents = 0;
				String expected = "$123,456.00";
				doTest(dollars, cents, expected, cs);
				break;
			}
			case 1: {
				int dollars = 49734321;
				int cents = 9;
				String expected = "$49,734,321.09";
				doTest(dollars, cents, expected, cs);
				break;
			}
			case 2: {
				int dollars = 0;
				int cents = 99;
				String expected = "$0.99";
				doTest(dollars, cents, expected, cs);
				break;
			}
			case 3: {
				int dollars = 249;
				int cents = 30;
				String expected = "$249.30";
				doTest(dollars, cents, expected, cs);
				break;
			}
			case 4: {
				int dollars = 1000;
				int cents = 1;
				String expected = "$1,000.01";
				doTest(dollars, cents, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(int dollars, int cents, String expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		FormatAmt instance = new FormatAmt();
		String result = "";
		try {
			result = instance.amount(dollars, cents);
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

	static int nExample = 5;
	static int nAll = 0, nPassed = 0;

	public static void main(String[] args){
		System.err.println("FormatAmt (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370384351;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
