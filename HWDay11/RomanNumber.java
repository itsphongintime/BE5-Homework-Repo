package HWDay11;

public class RomanNumber {
	public static int romanToInt (String s) {
		int result = 0;
		result = convertRightmostCharToInt(result, s.charAt(s.length() - 1));
		result = convertRomanStringToInt(result, s);
		return result;
	} // Time complexity O(1) + O(n - 1) = O(n)
	
	// Convert the rightmost char to integer and add it to result
	public static int convertRightmostCharToInt (int result, char rightmostChar) { // O(1)
		switch (rightmostChar) {
		case 'I':
			result++;
			break;
			
		case 'V':
			result += 5;
			break;
			
		case 'X':
			result += 10;
			break;
			
		case 'L':
			result += 50;
			break;
			
		case 'C':
			result += 100;
			break;
			
		case 'D':
			result += 500;
			break;
			
		case 'M':
			result += 1000;
			break;
		}
		return result;
	}
	
	public static int convertRomanStringToInt(int result, String s) {
		// Iterate from Right to Left of char array
		for (int i = s.length() - 2; i >= 0; i--) { // O(n - 1) where n is length of string
			// Add the integer value of each Roman letter to the result.
			switch (s.charAt(i)) {
			case 'I':
				result++;
				if (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X') {
					result -= 2;
				} // Subtraction instance for 'I'
				break;
						
			case 'V':
				result += 5;
				break;
						
			case 'X':
				result += 10;
				if (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C') {
					result -= 20;
				} // Subtraction instance for 'X'
				break;
						
			case 'L':
				result += 50;
				break;
						
			case 'C':
				result += 100;
				if (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M') {
					result -= 200;
				} // Subtraction instance for 'C'
				break;
						
			case 'D':
				result += 500;
				break;
						
			case 'M':
				result += 1000;
				break;
			}
		}
		return result;
	}
} 

