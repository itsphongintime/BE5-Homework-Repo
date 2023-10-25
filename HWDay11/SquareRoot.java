package HWDay11;

public class SquareRoot {
	public static void main(String[] args) {
		System.out.println(sqrt(17));
	}
	
	public static double sqrt(int n) {
		double result = n/2;
			
		for (int i = 0; i < 4; i++) {
			result = result - ((result*result) - n)/(2*result);
		}
		return result;
	}
} 
// O(1)

// Sau đó, dùng công thức ở loop dưới để tìm bên thập phân. Số loop phụ thuộc vào error margin. Đề này thầy cho error +- 0.01 thì e cho code chạy 4 lần. 
// Nếu error là 0.001 thì e tăng số loop lên 5 và các error margin khác cũng tương tự.
// Cái này cơ bản là O(1).

