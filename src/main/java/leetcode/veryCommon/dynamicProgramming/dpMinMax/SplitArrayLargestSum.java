package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class SplitArrayLargestSum {
    //[7,2,5,10,8]
    //[7,9,14,24,32]
    int n;
    int[] nums;
    int[] prefixSum;
    int[][] memo;

    public int splitArray(int[] nums, int m) {
        this.n = nums.length;
        this.nums = nums;
        this.prefixSum = new int[n];
        this.prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        this.memo = new int[n][m + 1];
        return helper(0, m);
    }

    public int helper(int start, int m) {
        if (m == 1)
            return prefixSum[n - 1] - (start - 1 < 0 ? 0 : prefixSum[start - 1]);

        if (n - start == m)
            return Arrays.stream(this.nums, start, this.n).max().getAsInt();

        if (memo[start][m] > 0) return memo[start][m];

        if (start >= n) return -1;

        int res = Integer.MAX_VALUE;
        for (int i = start + 1; i < n; i++) {
            if (nums[i - 1] == nums[i])
                continue;
            int prevSum = prefixSum[i - 1] - (start - 1 < 0 ? 0 : prefixSum[start - 1]);
            res = Math.min(res, Math.max(prevSum, helper(i, m - 1)));
        }

        memo[start][m] = res;

        return res;
    }
}
