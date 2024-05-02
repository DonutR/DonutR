package leetcode.veryCommon.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

//Kadane’s Algorithm:
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        int sum = Arrays.stream(A).sum();
        int maxSum = maxSubArraySum(A);
        IntStream.range(0, A.length).forEach(i -> A[i] = -1 * A[i]);
        int minSum = maxSubArraySum(A);
        int circularSum = sum + minSum;
        if (circularSum == 0)
            return maxSum;
        return Math.max(maxSum, circularSum);
    }

    public static void main(String[] args) {
        int[] a = {-2, -1, -3};
        System.out.println("Maximum contiguous sum is " +
                maxSubArraySum(a));
    }

    static int maxSubArraySum(int a[]) {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
}
