package HWDay11;

public class SquareRoot {
	public static void main(String[] args) {
		System.out.println(sqrt(17));
	}
	
	public static double sqrt(int n) {
		int result = 0;
		
		while (result*result < n) {
			result++;
		}
			
		for (int i = 0; i < 3; i++) {
			result = result - ((result*result) - n)/(2*result);
		}
		return result;
	}
} 
// O(n)
// Code này dùng Newton Raphson method. E dùng loop đầu để tính số mà khi nhân với chính nó bé hơn và gần với input nhất.
// Ví dụ: input là 15, thì loop đầu này sẽ return 3 vào result, vì 3*3 = 9 bé hơn và sát với 15 nhất. Mặc dù 4*4 = 16 gần 15 hơn, nhưng 16 > 15 nên ko đc.
// Thì n ở đây là số loop chạy để tìm ra cái số này.

// Sau đó, dùng công thức ở loop dưới để tìm bên thập phân. Số loop phụ thuộc vào error margin. Đề này thầy cho error +- 0.01 thì e cho code chạy 3 lần. 
// Nếu error là 0.001 thì e tăng số loop dưới lên 4 và các error margin khác cũng tương tự.
// Cái này cơ bản là O(1).
