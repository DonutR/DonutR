package leetcode.veryCommon.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    public static void main(String[] args) {
        int[][] inp = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(inp));
    }

    public static int orangesRotting(int[][] grid) {
        int[][] idxM = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int min = 0;
        int m = grid.length, n = grid[0].length;
        Queue<Integer[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 2)
                    q.add(new Integer[]{i, j});

        q.add(null);
        while (!q.isEmpty()) {
            Integer[] rotLoc = q.poll();
            if (rotLoc == null && !q.isEmpty()) {
                min++;
                q.add(null);
            } else if (rotLoc != null)
                for (int i = 0; i < 4; i++) {
                    int tmpI = rotLoc[0] + idxM[i][0], tmpJ = rotLoc[1] + idxM[i][1];
                    if (tmpI >= 0 && tmpJ >= 0 && tmpI < m && tmpJ < n)
                        if (grid[tmpI][tmpJ] == 1) {
                            q.add(new Integer[]{tmpI, tmpJ});
                            grid[tmpI][tmpJ] = 0;
                        }
                }
        }
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1)
                    return -1;
        return min;
    }
}