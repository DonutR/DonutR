package leetcode.veryCommon.dynamicProgramming;

import java.util.Arrays;

/**
 * This method relies on the fact that the longest increasing subsequence possible upto the i^{th}ith
 * index in a given array is independent of the elements coming later on in the array. Thus, if we know the length of the LIS upto i^{th}ith
 * index, we can figure out the length of the LIS possible by including the (i+1)^{th}(i+1)th
 * element based on the elements with indices jj such that 0 \leq j \leq (i + 1)0≤j≤(i+1).
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
