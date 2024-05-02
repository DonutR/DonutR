package random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LIS {
    public int deleteAndEarn(int[] nums) {
        int maxNum = Arrays.stream(nums).max().getAsInt();
        int[] dp = new int[maxNum + 1];
        Arrays.stream(nums).forEach(e -> dp[e] += e);

        for (int i = 2; i <= maxNum; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + dp[i]);
        }

        return dp[maxNum];
    }
}
