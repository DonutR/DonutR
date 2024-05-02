package leetcode.veryCommon.hash;

import java.util.HashSet;

public class NumberofDistinctIslands {
    int[][] dirSet = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int numDistinctIslands(int[][] grid) {
        HashSet<String> isLandSet = new HashSet<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    path.append(0+"");
                    helper(grid, i, j, path);
                    isLandSet.add(path.toString());
                }
            }
        return isLandSet.size();
    }

    public void helper(int[][] grid, int i, int j, StringBuilder path) {
        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            int nI = i + dirSet[d][0], nJ = j + dirSet[d][1];
            if (nI >= 0 && nI < grid.length && nJ >= 0 && nJ < grid[0].length && grid[nI][nJ] == 1) {
                path.append(d + "");
                helper(grid, nI, nJ, path);
            }
        }
    }
}
