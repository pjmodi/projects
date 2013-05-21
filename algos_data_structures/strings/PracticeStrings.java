import java.util.*;

class PracticeStrings {
	public static void main(String[] args) {
		try {
			StringUtil s = new StringUtil();
			s.reverseStringWords("This is a sample string");

		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			// Do something
		}
	}
}