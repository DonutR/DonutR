package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int sqrt_nums[] = new int[(int) Math.sqrt(n) + 1];

        for (int i = 1; i * i <= n; i++)
            sqrt_nums[i] = i * i;

        for (int j = 1; j <= n; j++) {
            for (int s = 1; s < sqrt_nums.length; s++) {
                if (j < sqrt_nums[s])
                    break;
                dp[j] = Math.min(dp[j], dp[j - sqrt_nums[s]] + 1);
            }
        }
        return dp[n];
    }
}
