package HWDay12;

import java.util.ArrayList;
import java.util.HashMap;

public class findOddNum {
	public static void main(String[] args) {
		int[] result = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 100, 400, 15, 15, 15, 15, 15, 15};
		int[] test = findOddTimesAppearNum(result);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
	
	public static int[] findOddTimesAppearNum (int[] arr) {
		HashMap<Integer, Integer> countMap = new HashMap<>();
		
		for (int i = 0; i < arr.length; i++) {
			int currVal = arr[i];
			if (countMap.get(currVal) == null) {
				countMap.put(currVal, 1);
			} else {
				countMap.put(currVal, countMap.get(currVal) + 1);
			}
		}
		
		ArrayList<Integer> oddAppearanceList = new ArrayList<Integer>();
		for (int i : countMap.keySet()) {
			if (countMap.get(i) % 2 == 1) {
				oddAppearanceList.add(i);
			}
		}

		int[] result = new int[oddAppearanceList.size()];
		for (int i = 0; i < oddAppearanceList.size(); i++) {
		    result[i] = oddAppearanceList.get(i);
		}
		return result;
	}
} //O(n)
