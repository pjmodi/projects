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

	public static Character findFirstNonRepeatedChar(String input) {
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();

		for(int i=0; i<input.length(); i++) {
			char c = input.charAt(i);

			if (c == ' ') {
				continue;
			}

			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}

		// System.out.println(map);	

		// See which one's have 1
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}

		return null;
	}

	private static void swap(char[] s, int i, int j) {
		if (i!=j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
		}
	}

}