package leetcode.veryCommon.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland {
    int m, n;
    int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        Map<Integer, Integer> islandSize = getIslandsSize(grid);
        int maxSize = islandSize.entrySet().stream().map(es -> es.getValue()).reduce(0, Integer::max);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                Set<Integer> distinctIsLands = new HashSet<>();
                if (grid[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nR = i + dir[k][0], nC = j + dir[k][1];
                        if (nR >= 0 && nC >= 0 && nR < m && nC < m && grid[nR][nC] != 0)
                            distinctIsLands.add(grid[nR][nC]);
                    }
                }
                int size = distinctIsLands.stream().map(id -> islandSize.get(id)).reduce(0, Integer::sum);
                maxSize = Math.max(maxSize, size + 1);
            }
        return maxSize;
    }

    public Map<Integer, Integer> getIslandsSize(int[][] grid) {
        Map<Integer, Integer> islandSize = new HashMap();
        int isLandId = 2;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfsHelper(grid, i, j, isLandId);
                    islandSize.put(isLandId++, size);
                }
            }
        return islandSize;
    }

    public int dfsHelper(int[][] grid, int r, int c, int isLandId) {
        int size = 0;
        grid[r][c] = isLandId;
        for (int i = 0; i < 4; i++) {
            int nR = r + dir[i][0], nC = c + dir[i][1];
            if (nR >= 0 && nC >= 0 && nR < m && nC < m && grid[nR][nC] == 1)
                size += dfsHelper(grid, nR, nC, isLandId);
        }
        return size + 1;
    }
}
