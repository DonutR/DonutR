package leetcode.veryCommon.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShortestPathtoGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        int steps = 0;

        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = grid.length, n = ((m > 0) ? grid[0].length() : 0);
        HashSet<String> visited = new HashSet<String>();
        Queue<ArrayList<Integer>> q = new LinkedList<>();

        ArrayList<Integer> keysState = new ArrayList(8);
        ArrayList<Integer> initialKeyState = new ArrayList(8);
        IntStream.range(0, 8).forEach(i -> keysState.add(0));
        IntStream.range(0, 8).forEach(i -> initialKeyState.add(0));

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c >= 'a' && c <= 'z')
                    keysState.set(c - 'a', 1);
                else if (c == '@') {
                    initialKeyState.set(6, i);
                    initialKeyState.set(7, j);
                    q.add(initialKeyState);
                    q.add(null);
                    visited.add(initialKeyState.stream().map(String::valueOf).collect(Collectors.joining(",")));
                }
            }
        if (m == 0 || keysState.subList(0, 6).stream().reduce(0, Integer::sum) == 0) return 0;

        while (!q.isEmpty()) {
            ArrayList<Integer> currPos = q.poll();
            if (currPos == null) {
                steps++;
                if (q.size() > 0) q.add(null);
            } else if (currPos.subList(0, 6).equals(keysState.subList(0, 6))) {
                return steps;
            } else {
                for (int[] d : dir) {
                    int nX = currPos.get(6) + d[0], nY = currPos.get(7) + d[1];
                    if (nX >= 0 && nX < m && nY >= 0 && nY < n) {
                        char c = grid[nX].charAt(nY);
                        if (c == '#')
                            continue;
                        if (c >= 'A' && c <= 'Z' && currPos.get(c + 32 - 'a') == 0)
                            continue;
                        ArrayList<Integer> nextPos = new ArrayList<>(currPos);
                        nextPos.set(6, nX);
                        nextPos.set(7, nY);
                        if (c >= 'a' && c <= 'z')
                            nextPos.set(c - 'a', 1);
                        String nextPosSnapShot = nextPos.stream().map(String::valueOf).collect(Collectors.joining(","));
                        if (!visited.contains(nextPosSnapShot)) {
                            //System.out.println(nextPosSnapShot);
                            visited.add(nextPosSnapShot);
                            q.add(nextPos);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
