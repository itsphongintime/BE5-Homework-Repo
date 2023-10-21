package HWDay11;

public class LongestSameString {
	public static String solution (String[] s) {
		int index = 0;
		
		while (s[0].charAt(index) == s[1].charAt(index) && index < s[0].length() - 1 && index < s[1].length() - 1) {
			index++;
		}
		
		String result = s[0].substring(0, index + 1);
		
		boolean isCommon = false;
		while (isCommon == false) {
			for (int i = 0; i < s.length; i++) {
				if (s[i].substring(0, index + 1).equals(result)) {
					isCommon = true;
				} else {
					result = result.substring(0, index);
					index--;
					isCommon = false;
					break;
				}
			}
		}
		return result;
	}
} 
// Time complexity: O(n*m)
// n is the amount of strings in the array
// m is the difference between the initial common substring between the first 2 strings of the String array and the final return substring.
