import java.util.*;

class PracticeStrings {
	public static void main(String[] args) {
		try {
			StringUtil s = new StringUtil();
			
			String a = s.reverseStringWords("This is a sample string");
			System.out.println(a);

			String b = s.reverseString("Reverse this string");
			System.out.println(b);

			

		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Do something
		}
	}
}