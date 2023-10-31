package HWDay13;

public class TheFeast {
	public static void main(String[] args) {
		System.out.println(theFeast(10, 2, 5));
		System.out.println(theFeast(12, 4, 4));
		System.out.println(theFeast(6, 2, 2));
	}
	
	public static int theFeast (int n, int c, int m) {
		int barCount = n/c;
		if (barCount < m) {
			return barCount;
		}
		int wrapperCount = barCount;
		while (wrapperCount >= m) {
			int tradedBars = wrapperCount/m;
			barCount += tradedBars;
			wrapperCount = wrapperCount - tradedBars*(m - 1);
		}
		return barCount;
	}
} // O(n/c) = O(n*(1/c)) = O(n)
