package leetcode.veryCommon.dynamicProgramming;

import java.util.Arrays;

public class JumpGameV {
    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            jumpCt(arr, dp, d, i);
        Arrays.stream(dp).forEach(System.out::println);
        return Arrays.stream(dp).max().getAsInt();
    }

    public int jumpCt(int[] arr, int[] dp, int d, int i) {
        if (dp[i] > 0)
            return dp[i];
        int maxIdx = Math.min(arr.length - 1, i + d);
        int minIdx = Math.max(0, i - d);
        int lCt = 0, rCt = 0;
        for (int k = i + 1; k <= maxIdx; k++)
            if (arr[i] > arr[k])
                rCt = Math.max(rCt, jumpCt(arr, dp, d, k));
            else break;

        for (int k = i - 1; k >= minIdx; k--)
            if (arr[i] > arr[k])
                lCt = Math.max(lCt, jumpCt(arr, dp, d, k));
            else break;

        dp[i] = 1 + Math.max(lCt, rCt);
        return dp[i];
    }
}
