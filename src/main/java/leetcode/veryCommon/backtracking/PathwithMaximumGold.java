package leetcode.veryCommon.backtracking;

public class PathwithMaximumGold {
    private static final int[] d = {0, 1, 0, -1, 0};

    public int getMaximumGold(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                ans = Math.max(ans, dfs(grid, i, j, 0));
            }
        }
        return ans;
    }

    private int dfs(int[][] g, int i, int j, int sum) {
        if (i >= 0 && i < g.length && j >= 0 && j < g[0].length) {
            int val = g[i][j];
            if (val > 0) {
                sum += g[i][j];
                g[i][j] = 0;
                int mx = 0;
                for (int k = 0; k < 4; ++k) { // traverse 4 neighbors to get max value.
                    mx = Math.max(mx, dfs(g, i + d[k], j + d[k + 1], sum));
                }
                g[i][j] = val;
                return mx;
            }
        }
        return sum;
    }
}
