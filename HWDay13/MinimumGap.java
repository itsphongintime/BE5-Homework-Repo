package HWDay13;

import java.util.Arrays;

public class MinimumGap {
	public static void main(String[] args) {
		int[] arr = {7,1,3,4,1,7};
		System.out.println(minimumGap(arr));
	}
	
	public static int minimumGap (int[] arr) {
		int minGap = arr.length + 1;
		int[][] indexedArr = new int[arr.length][2];
		
		for (int i = 0; i < arr.length; i++) {
			indexedArr[i][0] += arr[i];
			indexedArr[i][1] += i;
		}
		
		Arrays.sort(indexedArr, (a, b) -> {
		    if(a[0] == b[0]) {
		        return a[1] - b[1];
		    }
		    return a[0] - b[0];
		});

		
		for (int i = 0; i < arr.length - 1; i++) {
			if (indexedArr[i][0] == indexedArr[i + 1][0]) {
				minGap = Math.min(minGap, indexedArr[i + 1][1] - indexedArr[i][1]);
			}
		}
		
		if (minGap == arr.length + 1) {
			return - 1;
		} else {
			return minGap;
		}
	}
} // O(nlogn)
