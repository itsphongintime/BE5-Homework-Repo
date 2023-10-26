package HWDay12;

public class findSquareIntegers {
	public static void main(String[] args) {
		System.out.println(squares(3, 9));
	}
	
	public static int squares(int a, int b) {
		int squareIntCount = 0;
		for (double i = a; i <= b; i++) {
			double squareRootCurNum = Math.sqrt(i);
			int doubleToInt = (int) squareRootCurNum;
			if (squareRootCurNum - (double) doubleToInt == 0) {
				squareIntCount++;
			}
		}
		return squareIntCount;
	}
} //O(n) 
