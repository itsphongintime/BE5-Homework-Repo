package ADSPractice;

public class MaxSumViaDeletion {
    public static long solution(int[] A) {
        long evenSum = 0;
        long oddSum = 0;

        for (int number : A) {
            long newEvenSum = Math.max(evenSum, (oddSum + number));
            long newOddSum = Math.max(oddSum, (evenSum - number));

            evenSum = newEvenSum;
            oddSum = newOddSum;
        }

        long result = Math.max(evenSum, oddSum);
        return result % 1000000000;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{4, 1, 2, 3}));
        System.out.println(solution(new int[]{1, 2, 3, 2, 1, 5}));
        System.out.println(solution(new int[]{1000000000, 1, 2, 2, 1000000000, 1, 1000000000}));
    }
}
