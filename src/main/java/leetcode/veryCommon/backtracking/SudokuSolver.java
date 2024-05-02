package leetcode.veryCommon.backtracking;

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char canChar = '1'; canChar <= '9'; canChar++) {
                        if (isValid(board, canChar, i, j)) {
                            board[i][j] = canChar;//Put c for this cell
                            if (solve(board))
                                return true;//If it's the solution return true
                            board[i][j] = '.';//Otherwise backtrack
                        }
                    }
                    return false;
                }
            }
        return true;
    }

    public boolean isValid(char[][] board, char c, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;
            if (board[i][col] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
}
