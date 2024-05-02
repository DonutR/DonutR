package leetcode.veryCommon.random;

/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * Example 2:
 * <p>
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * * [
 * *   [ 5, 1, 9,11, 12],
 * *   [ 2, 4, 8,10, 13],
 * *   [13, 3, 6, 7, 14],
 * *   [15,14,12,16, 15],
 * *   [15,14,12,16, 15]
 * * ],
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        for (int i = 0; i <= m / 2; i++) {
            int mm = m - i - 1;
            int tmp = 0, tmp2 = 0, tmp3 = 0;
            for (int j = i; j <= mm; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[mm - j][i];
                tmp2 = matrix[j][mm];
                matrix[j][mm] = tmp;
                tmp3 = matrix[mm - i][mm - j];
                matrix[mm - i][mm - j] = tmp2;
                matrix[mm][j] = tmp3;
            }
        }
    }
}
