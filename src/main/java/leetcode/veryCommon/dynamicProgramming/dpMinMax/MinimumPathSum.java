package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.stream.IntStream;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        IntStream.range(1, n).forEach(i -> grid[0][i] += grid[0][i - 1]);
        IntStream.range(1, m).forEach(i -> grid[i][0] += grid[i - 1][0]);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }
}
