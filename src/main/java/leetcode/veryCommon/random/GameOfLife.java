package leetcode.veryCommon.random;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * Output:
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {
    public static void gameOfLife(int[][] board) {
        recFun(board, 0, 0, new boolean[board.length][board[0].length]);
    }

    public static void recFun(int[][] board, int x, int y, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visited[x][y])
            return;
        visited[x][y] = true;
        int ct = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i, ny = y + j;
                if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length && (i != 0 || j != 0)) {
                    ct += board[nx][ny];
                }
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + i, ny = y + j;
                recFun(board, nx, ny, visited);
            }
        }
        if (board[x][y] == 1 && ct < 2) board[x][y] = 0;
        else if (board[x][y] == 1 && (ct == 2 || ct == 3)) board[x][y] = 1;
        else if (board[x][y] == 1 && ct > 3) board[x][y] = 0;
        else if (board[x][y] == 0 && ct == 3) board[x][y] = 1;
    }

    public static void gameOfLife2(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbourCt = 0;
                for (int ii = -1; ii <= 1; ii++) {
                    for (int jj = -1; jj <= 1; jj++) {
                        int nx = ii + i, ny = jj + j;
                        if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length && (ii != 0 || jj != 0)) {
                            if (board[nx][ny] != 2)
                                liveNeighbourCt += Math.abs(board[nx][ny]);
                        }
                    }
                }
                if (board[i][j] == 1 && (liveNeighbourCt < 2 || liveNeighbourCt > 3)) board[i][j] = -1;
                else if (board[i][j] == 0 && liveNeighbourCt == 3) board[i][j] = 2;
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = board[i][j] > 0 ? 1 : 0;
    }

}
