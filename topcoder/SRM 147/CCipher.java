public class CCipher {
	public String decode(String cipherText, int shift) {
		char[] cipher = cipherText.toCharArray();
		for(int i=0; i<cipher.length; i++) {
			cipher[i] = convert(cipher[i], shift);
		}
		return String.valueOf(cipher);
	}

	public char convert(char swap, int shift) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] aSoup = alphabet.toCharArray();

		int index = 0;
		for(int i=0; i<aSoup.length; i++) {
			if (swap == aSoup[i]) {
				index = i;
				break;
			}
		}

		int newChar = index - shift;
		if (newChar < 0) {
			newChar += 26;
		}

		return aSoup[newChar];
	}

// CUT begin
	static void runTestcase(int cs) {
		switch (cs) {
			// Your custom testcase goes here
			case -1:
				//doTest(cipherText, shift, expected, cs);
				break;

			case 0: {
				String cipherText = "VQREQFGT";
				int shift = 2;
				String expected = "TOPCODER";
				doTest(cipherText, shift, expected, cs);
				break;
			}
			case 1: {
				String cipherText = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				int shift = 10;
				String expected = "QRSTUVWXYZABCDEFGHIJKLMNOP";
				doTest(cipherText, shift, expected, cs);
				break;
			}
			case 2: {
				String cipherText = "TOPCODER";
				int shift = 0;
				String expected = "TOPCODER";
				doTest(cipherText, shift, expected, cs);
				break;
			}
			case 3: {
				String cipherText = "ZWBGLZ";
				int shift = 25;
				String expected = "AXCHMA";
				doTest(cipherText, shift, expected, cs);
				break;
			}
			case 4: {
				String cipherText = "DBNPCBQ";
				int shift = 1;
				String expected = "CAMOBAP";
				doTest(cipherText, shift, expected, cs);
				break;
			}
			case 5: {
				String cipherText = "LIPPSASVPH";
				int shift = 4;
				String expected = "HELLOWORLD";
				doTest(cipherText, shift, expected, cs);
				break;
			}
			default: break;
		}
	}

	static void doTest(String cipherText, int shift, String expected, int caseNo) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		CCipher instance = new CCipher();
		String result = "";
		try {
			result = instance.decode(cipherText, shift);
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

	static int nExample = 6;
	static int nAll = 0, nPassed = 0;

	public static void main(String[] args){
		System.err.println("CCipher (250 Points)");
		System.err.println();
		if (args.length == 0)
			for (int i = 0; i < nExample; ++i) runTestcase(i);
		else
			for (int i = 0; i < args.length; ++i) runTestcase(Integer.parseInt(args[i]));
		System.err.println(String.format("%nPassed : %d/%d cases", nPassed, nAll));

		int T = (int)(System.currentTimeMillis() / 1000) - 1370381646;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}
// CUT end
}
