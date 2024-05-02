package leetcode.veryCommon.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SurroundedRegions {
    int[][] diff = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] grid) {
        if (grid.length == 0) return;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1)
                    dfsFlip(grid, i, j, m, n, 'O', 'Y');

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dfsFlip(grid, i, j, m, n, 'O', 'X');

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dfsFlip(grid, i, j, m, n, 'Y', 'O');
    }

    public void dfsFlip(char[][] grid, int i, int j, int m, int n, char src, char tgt) {
        if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == src) {
            grid[i][j] = tgt;
            for (int[] xy : diff)
                dfsFlip(grid, i + xy[0], j + xy[1], m, n, src, tgt);
        }
    }

}
