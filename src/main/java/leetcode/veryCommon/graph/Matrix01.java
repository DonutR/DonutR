package leetcode.veryCommon.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {

    public int[][] updateMatrix(int[][] matrix) {
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = matrix.length, n = (m > 0 ? matrix[0].length : 0);
        int[][] out = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1)
                    out[i][j] = Integer.MAX_VALUE;
                else
                    q.add(new int[]{i, j});
            }
        while (!q.isEmpty()) {
            int[] currPos = q.poll();
            for (int[] d : dir) {
                int nI = currPos[0] + d[0], nJ = currPos[1] + d[1];
                if (nI >= 0 && nI < m && nJ >= 0 && nJ < n)
                    if (out[nI][nJ] > out[currPos[0]][currPos[1]]) {
                        out[nI][nJ] = out[currPos[0]][currPos[1]] + 1;
                        q.add(new int[]{nI, nJ});
                    }
            }
        }
        return out;
    }
}
