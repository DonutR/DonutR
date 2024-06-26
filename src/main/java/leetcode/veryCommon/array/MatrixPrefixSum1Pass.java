package leetcode.veryCommon.array;

// Maximum Side Length of a Square with Sum Less than or Equal to Threshold
//Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
//
//
//
//        Example 1:
//
//
//        Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
//        Output: 2
//        Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
//        Example 2:
//
//        Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
//        Output: 0
//        Example 3:
//
//        Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
//        Output: 3
//        Example 4:
//
//        Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
//        Output: 2
//
//
//        Constraints:
//
//        1 <= m, n <= 300
//        m == mat.length
//        n == mat[i].length
//        0 <= mat[i][j] <= 10000
//        0 <= threshold <= 10^5

import java.util.Arrays;

public class MatrixPrefixSum1Pass {
    public static void main(String[] args) {
        int[][] inp = {{1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}};
        maxSideLength(inp, 4);
    }

    public static int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        // Find prefix sum
        int[][] prefixSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += mat[i - 1][j - 1];
                prefixSum[i][j] = prefixSum[i - 1][j] + sum;
            }
        }

        Arrays.stream(prefixSum).forEach(a -> {
            Arrays.stream(a).forEach(b -> System.out.print(b + " "));
            System.out.println();
        });
        // Start from the largest side length
        for (int k = Math.min(m, n) - 1; k > 0; k--) {
            for (int i = 1; i + k <= m; i++) {
                for (int j = 1; j + k <= n; j++) {
                    if (prefixSum[i + k][j + k] - prefixSum[i - 1][j + k] - prefixSum[i + k][j - 1] + prefixSum[i - 1][j - 1] <= threshold) {
                        return k + 1;
                    }
                }
            }
        }
        return 0;
    }
}
