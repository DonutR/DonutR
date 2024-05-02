package leetcode.veryCommon.stackMonotoneStackQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 * <p>
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 30000
 * 1 <= A[i] <= 30000
 */
public class SumOfSubarrayMinimums {

    public static int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;
        Stack<Integer> stack = new Stack<>();
        int n = A.length;
        int[] prev = new int[n], next = new int[n];
        Arrays.fill(prev, -1);
        Arrays.fill(next, n);

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[stack.peek()] > A[i])
                next[stack.pop()] = i;
            stack.push(i);
        }

        stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[stack.peek()] > A[i])
                prev[stack.pop()] = i;
            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += (i - prev[i]) * (next[i] - i) % MOD * A[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }
}
