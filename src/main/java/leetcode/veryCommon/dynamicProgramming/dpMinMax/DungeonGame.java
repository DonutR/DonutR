package leetcode.veryCommon.dynamicProgramming.dpMinMax;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                int[] nDown = {i + 1, j};
                int[] nRight = {i, j + 1};
                if (nDown[0] == m && nRight[1] == n)
                    dp[i][j] = dungeon[i][j] < 0 ? (dungeon[i][j] * -1) + 1 : 1;
                else if (nDown[0] == m && nRight[1] < n)
                    dp[i][j] = dungeon[i][j] < 0 ?
                            (dungeon[i][j] * -1) + dp[nRight[0]][nRight[1]] :
                            dungeon[i][j] - dp[nRight[0]][nRight[1]] > 0 ?
                                    1 : Math.max(1, dp[nRight[0]][nRight[1]] - dungeon[i][j]);
                else if (nDown[0] < m && nRight[1] == n)
                    dp[i][j] = dungeon[i][j] < 0 ?
                            (dungeon[i][j] * -1) + dp[nDown[0]][nDown[1]] :
                            dungeon[i][j] - dp[nDown[0]][nDown[1]] > 0 ?
                                    1 : Math.max(1, dp[nDown[0]][nDown[1]] - dungeon[i][j]);
                else {
                    int min = Math.min(dp[nDown[0]][nDown[1]], dp[nRight[0]][nRight[1]]);
                    dp[i][j] = dungeon[i][j] < 0 ? (dungeon[i][j] * -1) + min :
                            dungeon[i][j] - min > 0 ?
                                    1 : Math.max(1, min - dungeon[i][j]);
                }
            }
        return dp[0][0];
    }
}
