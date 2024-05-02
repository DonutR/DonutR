package leetcode.veryCommon.graph;

public class NumberofClosedIslands {
    int[][] diff = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int closedIsland(int[][] grid) {
        int out = 0;
        int m = grid.length, n = (m > 0 ? grid[0].length : 0);
        for (int i = 1; i < m - 1; i++)
            for (int j = 1; j < n - 1; j++)
                if (grid[i][j] == 0 && !helper(grid, i, j, m, n))
                    out++;

        return out;
    }

    public boolean helper(int[][] grid, int i, int j, int m, int n) {
        grid[i][j] = 1;
        boolean reachedEdge = false;
        for (int[] xy : diff) {
            int nI = i + xy[0], nJ = j + xy[1];
            if (nI >= 0 && nI < m && nJ >= 0 && nJ < n) {
                if (grid[nI][nJ] == 0) {
                    //Very important: AND/OR optimization
                    boolean tmp = helper(grid, nI, nJ, m, n);
                    reachedEdge = reachedEdge || tmp;
                }
            } else reachedEdge = true;
        }
        return reachedEdge;
    }
}
