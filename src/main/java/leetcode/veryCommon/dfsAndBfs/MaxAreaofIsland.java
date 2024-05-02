package leetcode.veryCommon.dfsAndBfs;

import java.util.LinkedList;
import java.util.Queue;

public class MaxAreaofIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int out = 0;
        int m = grid.length, n = grid[0].length;
        int[][] diff = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                out = Math.max(out, helper(grid, diff, i, j, m, n));
        return out;
    }

    public int helper(int[][] grid, int[][] diff, int i, int j, int m, int n) {
        if (i >= 0 && j >= 0 && i < m && j < n && grid[i][j] == 1) {
            int sm = 0;
            grid[i][j] = 0;
            for (int k = 0; k < 4; k++) {
                sm += helper(grid, diff, i + diff[k][0], j + diff[k][1], m, n);
            }
            return sm + 1;
        }
        return 0;
    }
}