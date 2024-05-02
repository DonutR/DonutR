package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class MinimumFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[] dp = matrix[0].clone();

        for (int i = 1; i < n; i++) {
            int[] tmpDp = dp.clone();
            for (int j = 0; j < n; j++) {
                tmpDp[j] = Math.min(
                        Math.min(j - 1 < 0 ? Integer.MAX_VALUE : dp[j - 1] + matrix[i][j], dp[j] + matrix[i][j]),
                        j + 1 >= n ? Integer.MAX_VALUE : dp[j + 1] + matrix[i][j]);
            }
            dp = tmpDp;
        }
        return Arrays.stream(dp).min().getAsInt();
    }
}
