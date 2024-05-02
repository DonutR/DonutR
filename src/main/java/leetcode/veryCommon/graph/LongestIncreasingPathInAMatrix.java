package leetcode.veryCommon.graph;

public class LongestIncreasingPathInAMatrix {
    int[][] diff = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath1(int[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, out = 0;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                out = Math.max(out, helper(matrix, i, j, 1, m, n, memo));
        return out;
    }

    public int helper(int[][] matrix, int i, int j, int length, int m, int n, int[][] memo) {
        int ret = length;
        if (memo[i][j] > 0)
            return length + memo[i][j];
        for (int k = 0; k < 4; k++) {
            int nI = i + diff[k][0], nJ = j + diff[k][1];
            if (nI >= 0 && nJ >= 0 && nI < m && nJ < n && matrix[i][j] < matrix[nI][nJ]) {
                ret = Math.max(ret, helper(matrix, nI, nJ, length + 1, m, n, memo));
            }
        }
        memo[i][j] = ret - length;
        return ret;
    }

    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length, out = 0;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                out = Math.max(out, helper(matrix, i, j, memo) + 1);
        return out;
    }

    public int helper(int[][] matrix, int i, int j, int[][] memo) {
        int ret = 0;
        if (memo[i][j] > 0)
            return memo[i][j];
        for (int k = 0; k < 4; k++) {
            int nI = i + dir[k][0], nJ = j + dir[k][1];
            if (nI >= 0 && nJ >= 0 && nI < matrix.length && nJ < matrix[0].length && matrix[i][j] < matrix[nI][nJ])
                ret = Math.max(ret, helper(matrix, nI, nJ, memo));
        }
        memo[i][j] = ret + 1;
        return memo[i][j];
    }
}
