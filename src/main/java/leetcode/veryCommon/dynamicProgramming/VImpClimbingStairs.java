package leetcode.veryCommon.dynamicProgramming;

public class VImpClimbingStairs {

    public static int climbStairsDP(int n) {
        int[] ways = {1, 2};
        int[] dp = new int[n + ways.length];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < n + ways.length; i++) {
            for (int j = 0; j < ways.length; j++) {
                dp[i] += dp[i - ways[j]];
            }
        }
        return dp[n + ways.length - 1];
    }


    public int climbStairs(int n) {
        Integer memo[] = new Integer[n + 1];
        return topDownMemo(n, memo);
    }

    public int topDownMemo(int n, Integer memo[]) {
        if (n <= 1)
            return 1;
        if (memo[n] != null)
            return memo[n];
        memo[n] = topDownMemo(n - 1, memo) + topDownMemo(n - 2, memo);
        return memo[n];
    }
}
