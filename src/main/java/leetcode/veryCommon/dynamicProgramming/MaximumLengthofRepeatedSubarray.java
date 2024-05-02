package leetcode.veryCommon.dynamicProgramming;

public class MaximumLengthofRepeatedSubarray {
    //Bottom Up
    public int findLength1(int[] A, int[] B) {
        int[][] memo = new int[A.length + 1][B.length + 1];
        int max = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j])
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                if (max < memo[i][j]) max = memo[i][j];
            }
        }
        return max;
    }

    //Top Down
    public int findLength(int[] A, int[] B) {
        int[][] memo = new int[A.length + 1][B.length + 1];
        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1])
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                if (max < memo[i][j]) max = memo[i][j];
            }
        }
        return max;
    }
}
