package leetcode.veryCommon.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    int[][] diff = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestBridge(int[][] A) {
        if (A.length == 0) return 0;
        int m = A.length, n = A[0].length, out = 0;
        Queue<Integer[]> q = new LinkedList<>();
        outer:
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (A[i][j] == 1) {
                    dfs(A, i, j, m, n, q);
                    break outer;
                }
        q.add(null);
        while (!q.isEmpty()) {
            Integer[] currXy = q.poll();
            if (currXy == null) {
                out++;
                if (!q.isEmpty())
                    q.add(null);
            } else {
                for (int[] xy : diff) {
                    int i = currXy[0] + xy[0], j = currXy[1] + xy[1];
                    Integer[] newXy = new Integer[]{i, j};
                    if (i >= 0 && i < m && j >= 0 && j < n) {
                        if (A[i][j] == 0) {
                            A[i][j] = -1;
                            q.add(newXy);
                        } else if (A[i][j] == 1)
                            return out++;
                    }
                }
            }
        }
        return out;
    }

    public void dfs(int[][] A, int i, int j, int m, int n, Queue<Integer[]> out) {
        if (i >= 0 && i < m && j >= 0 && j < n && A[i][j] == 1) {
            Integer[] curXy = new Integer[]{i, j};
            out.add(curXy);
            A[i][j] = 0;
            for (int[] xy : diff) {
                dfs(A, i + xy[0], j + xy[1], m, n, out);
            }
        }
    }

}
