package leetcode.veryCommon.twoPointer;

/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * <p>
 * If there is no non-empty subarray with sum at least K, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 * <p>
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 * <p>
 * Input: A = [2,-1,3], K = 3
 * Output: 1
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 *
 * 1) Use a stack to push all elements.
 * 2) For each element if the sum >=k
 * 3) Pop the stack for all +ve value and take the sum
 */
public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        int sum = 0, st = 0, min = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            int tmpSm = sum;
            while (tmpSm >= K) {
                tmpSm = tmpSm - A[st];
                if (tmpSm < K) {
                    break;
                }
                sum = tmpSm;
                st++;
                if (min < i - st)
                    min = i - st;
            }
        }
        return min;
    }
}

