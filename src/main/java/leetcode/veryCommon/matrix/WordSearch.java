package leetcode.veryCommon.matrix;

public class WordSearch {
    static int[][] diff = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0))
                    if (recFun(board, i, j, m, n, new boolean[m][n], word, 0))
                        return true;
            }
        }
        return false;
    }

    public static boolean recFun(char[][] board, int i, int j, int m, int n, boolean[][] isVisited, String word, int charIdx) {
        boolean ret = false;
        if (charIdx >= word.length()) {
            return true;
        } else if (i < m && j < n && i >= 0 && j >= 0 && !isVisited[i][j]) {
            if (board[i][j] == word.charAt(charIdx)) {
                isVisited[i][j] = true;
                for (int k = 0; k < 4; k++) {
                    if (recFun(board, i + diff[k][0], j + diff[k][1], m, n, isVisited, word, charIdx + 1)){
                        ret=true;
                        break;
                    }
                }
                isVisited[i][j] = false;
            }
        }
        return ret;
    }
}

