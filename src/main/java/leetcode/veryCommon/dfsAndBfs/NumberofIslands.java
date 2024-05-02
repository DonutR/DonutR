package leetcode.veryCommon.dfsAndBfs;

public class NumberofIslands {
    int[][] newIdx = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        int out = 0;
        if (grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1') {
                    helper(grid, i, j, m, n);
                    out++;
                }
        return out;
    }

    public void helper(char[][] grid, int i, int j, int m, int n) {
        if (i >= 0 && j >= 0 && i < m && j < n) {
            if (grid[i][j] == '1') {
                grid[i][j] = '0';
                for (int k = 0; k < 4; k++) {
                    helper(grid, i + newIdx[k][0], j + newIdx[k][1], m, n);
                }
            }
        }
    }
}
