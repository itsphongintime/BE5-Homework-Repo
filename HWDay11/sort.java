package HWDay11;

public class sort {
	public static int[] sorting (int[] intArray) {
		// Initialize new array to copy elements later
		int[] result = new int[intArray.length];
		// Initialize the starting index and the ending index of the array
		int start = 0;
		int end = intArray.length - 1;
		
		for (int i = 0; i < intArray.length; i++) {
			if (intArray[i] == 0) { // Set values at the start of the "result" to 0, basically moving any zeroes found to the left
				start++;
			} else if (intArray[i] == 2) { // Set values at the end of the "result" to 2, basically moving any twos found to the right
				result[end] = 2;
				end--;
			}
		} // O(n)
		
		// The remaining positions in the "result" array that has not been touched will be set to 1
		for (int i = start; i <= end; i++) {
			result[i] = 1;
		} // O(m) (explanation at the end of code)
		
		return result;
	}
} 
// Time complexity: O(n) + O(m) = O(n)
// n is the length of the input array
// m is the amount of 1s in the array