package leetcode.veryCommon.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathinaGridwithObstaclesElimination {

    public int shortestPath(int[][] grid, int k) {
        int res = 0, m = grid.length, n = grid[0].length;
        Queue<Integer[]> q = new LinkedList<>();
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        q.add(new Integer[]{0, 0, k});
        q.add(null);
        HashSet<String> visitedSet = new HashSet<>();
        visitedSet.add(0 + "_" + 0 + "_" + k);
        while (!q.isEmpty()) {
            Integer[] curr = q.poll();
            if (curr == null) {
                res++;
                if (!q.isEmpty())
                    q.add(null);
            } else {
                if (curr[0] == m - 1 && curr[1] == n - 1)
                    return res;
                for (int i = 0; i < 4; i++) {
                    int nI = curr[0] + dir[i][0], nJ = curr[1] + dir[i][1];
                    if (nI < m && nI >= 0 && nJ < n && nJ >= 0) {
                        int newK = curr[2];
                        String nextState = nI + "_" + nJ + "_" + newK;
                        if (grid[nI][nJ] == 1) {
                            newK = curr[2] - 1;
                            nextState = nI + "_" + nJ + "_" + newK;
                        }
                        if (newK >= 0 && !visitedSet.contains(nextState)) {
                            q.add(new Integer[]{nI, nJ, newK});
                            visitedSet.add(nextState);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
