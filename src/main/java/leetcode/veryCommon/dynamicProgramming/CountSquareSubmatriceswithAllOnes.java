package leetcode.veryCommon.dynamicProgramming;

public class CountSquareSubmatriceswithAllOnes {
    public int countSquares(int[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int out = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    int a = (i - 1 >= 0 && j - 1 >= 0) ? matrix[i - 1][j - 1] : 0;
                    int b = (i - 1 >= 0) ? matrix[i - 1][j] : 0;
                    int c = (j - 1 >= 0) ? matrix[i][j - 1] : 0;
                    matrix[i][j] = Math.min(a, Math.min(b, c)) + matrix[i][j];
                }
                out+=matrix[i][j];
            }
        }
        return out;
    }
}
