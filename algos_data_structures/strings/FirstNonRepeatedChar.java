import java.util.*;

class FirstNonRepeatedChar {	

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

	public static void main(String[] args) {
		try {
			Character c = findFirstNonRepeatedChar("baa baa black sheep");
			System.out.println("First non-repeated character: " + c);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Do something
		}
	}

}