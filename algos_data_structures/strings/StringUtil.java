import java.util.*;

class StringUtil {
	public StringUtil() {
	}

	public static String reverseStringWords(String input) {
		String reverseInput = reverseString(input);
		String[] words = reverseInput.split(" ");
		StringBuilder sb = new StringBuilder();

		for(int i=0; i<words.length; i++) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append(reverseString(words[i]));
		}
		
		return sb.toString();
	}

	public static String reverseString(String input) {
		char[] s = input.toCharArray();
		
		for(int i=0; i<s.length/2; i++) {
			int j = s.length - 1 - i;
			swap(s, i, j);
		}

		return new String(s);
	}

	private static void swap(char[] s, int i, int j) {
		if (i!=j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
		}
	}

}