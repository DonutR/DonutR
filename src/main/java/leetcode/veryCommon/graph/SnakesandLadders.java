package leetcode.veryCommon.graph;

import java.util.*;

public class SnakesandLadders {
    public int snakesAndLadders(int[][] board) {
        int steps = 1, n = board.length, dest = n * n;
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(1);
        q.add(null);
        while (!q.isEmpty()) {
            Integer currPos = q.poll();
            if (currPos == null) {
                steps++;
                if (!q.isEmpty()) q.add(null);
            } else {
                visited.add(currPos);
                for (int i = 1; i <= 6 && currPos + i <= dest; i++) {
                    int newPos = currPos + i;
                    if (newPos == dest) return steps + 1;
                    int x = n - 1 - ((newPos - 1) / n), y = (newPos - 1) % n;
                    System.out.println(newPos + "::" + board[x][y]);
                    if (board[x][y] != -1) {
                        if (board[x][y] == dest)
                            return steps + 1;
                        if (!visited.contains(board[x][y]))
                            q.add(board[x][y]);
                    } else if (!visited.contains(newPos)) q.add(newPos);
                }
            }
        }
        return -1;
    }
}
