package leetcode.veryCommon.divideAndConquer;

public class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0)
            return false;

        int col = matrix[0].length - 1;
        int row = 0;

        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == target)
                return true;
            if (target > matrix[row][col]) {
                row++;
            } else col--;
        }
        return false;
    }
}
