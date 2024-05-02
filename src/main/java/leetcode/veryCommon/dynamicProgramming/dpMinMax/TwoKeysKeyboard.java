package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class TwoKeysKeyboard {
    public int minSteps(int n) {
//0  1  2  3  4  5  6  7  8  9 10
//0  1  2  3  4  5  5  7  6  6

        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    int factor = i / j;
                    dp[i] = Math.min(dp[i], dp[j] + factor - 1 + 1);
                }
            }
        }
        return dp[n];
    }
}
