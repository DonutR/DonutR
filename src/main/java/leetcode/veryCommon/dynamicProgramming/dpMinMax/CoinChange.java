package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class CoinChange {
    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int c = 0; c < coins.length; c++) {
                int nextSmall = i - coins[c];
                if (nextSmall >= 0)
                    dp[i] = Math.min(dp[i], dp[nextSmall] + 1);
                else break;
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        int res = coinChangeDfs(coins, amount);

        return res < 0 ? res : res - 1;
    }

    private int coinChangeDfs(int[] coins, int rem) {
        if (rem < 0) return -1;
        if (rem == 0) return 1;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeDfs(coins, rem - coin);
            min = res > 0 ? Math.min(min, res) : min;
        }
        return min == Integer.MAX_VALUE ? -1 : min + 1;
    }
}
