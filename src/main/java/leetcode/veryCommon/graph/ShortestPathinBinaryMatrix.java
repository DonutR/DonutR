package leetcode.veryCommon.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathinBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int out = 1,n=grid.length;
        int[] start = {0, 0}, target = {n - 1, n - 1};
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        q.add(null);
        while (!q.isEmpty()) {
            int[] curPos = q.poll();
            if (curPos == null) {
                out++;
                if (!q.isEmpty())
                    q.add(null);
            } else {
                if (Arrays.equals(curPos, target))
                    return out;
                for (int i = -1; i < 2; i++)
                    for (int j = -1; j < 2; j++) {
                        int nI = curPos[0] + i, nJ = curPos[1] + j;
                        if (nI >= 0 && nI < n && nJ >= 0 && nJ < n && grid[nI][nJ] == 0) {
                            int[] newPos = {nI, nJ};
                            grid[nI][nJ] = 1;
                            q.add(newPos);
                        }
                    }
            }
        }
        return -1;
    }
}
