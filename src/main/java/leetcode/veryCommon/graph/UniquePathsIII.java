package leetcode.veryCommon.graph;

import java.util.Arrays;

public class UniquePathsIII {
    int[][] grid;
    int ret, m, n;
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int freeNodeCt;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int sI = 0, sJ = 0;
        ret = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) freeNodeCt++;
                if (grid[i][j] == 1) {
                    sI = i;
                    sJ = j;
                }
            }
        dfs(sI, sJ, 0);
        return ret;
    }

    public void dfs(int i, int j, int count) {
        for (int d = 0; d < 4; d++) {
            int nI = i + dir[d][0], nJ = j + dir[d][1];
            if (nI < m && nI >= 0 && nJ < n && nJ >= 0) {
                if (grid[nI][nJ] == 2) {
                    if (freeNodeCt == count)
                        ret++;
                } else if (grid[nI][nJ] == 0) {
                    grid[nI][nJ] = 3;
                    dfs(nI, nJ, count + 1);
                    grid[nI][nJ] = 0;
                }
            }
        }
    }
}
