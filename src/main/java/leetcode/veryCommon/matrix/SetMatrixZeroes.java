package leetcode.veryCommon.matrix;

import java.util.HashSet;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        HashSet<Integer> rIdx = new HashSet<>();
        HashSet<Integer> cIdx = new HashSet<>();
        int m = matrix.length;
        if (m == 0)
            return;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) {
                    rIdx.add(i);
                    cIdx.add(j);
                }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (rIdx.contains(i) || cIdx.contains(j))
                    matrix[i][j] = 0;
        return;
    }
}
