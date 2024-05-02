package leetcode.veryCommon.graph;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        } else {
            int m = board.length, n = (m > 0 ? board[0].length : 0);
            Queue<int[]> q = new LinkedList<>();
            if (board[click[0]][click[1]] == 'E') {
                board[click[0]][click[1]] = 'B';
                q.add(click);
            }
            while (!q.isEmpty()) {
                int[] currNode = q.poll();
                int mineCt = 0;
                for (int i = -1; i < 2; i++)
                    for (int j = -1; j < 2; j++) {
                        int nX = currNode[0] + i, nY = currNode[1] + j;
                        int[] nextNode = new int[]{nX, nY};
                        if (nX >= 0 && nX < m && nY >= 0 && nY < n && board[nX][nY] == 'M' && !Arrays.equals(currNode, nextNode))
                            mineCt++;
                    }
                if (mineCt > 0) {
                    board[currNode[0]][currNode[1]] = (mineCt + "").charAt(0);
                } else {
                    for (int i = -1; i < 2; i++)
                        for (int j = -1; j < 2; j++) {
                            int nX = currNode[0] + i, nY = currNode[1] + j;
                            int[] nextNode = new int[]{nX, nY};
                            if (nX >= 0 && nX < m && nY >= 0 && nY < n && board[nX][nY] == 'E' && !Arrays.equals(currNode, nextNode)) {
                                board[nextNode[0]][nextNode[1]] = 'B';
                                q.add(nextNode);
                            }
                        }
                }
            }
            return board;
        }
    }
}
