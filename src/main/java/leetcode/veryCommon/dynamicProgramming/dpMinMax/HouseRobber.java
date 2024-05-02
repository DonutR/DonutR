package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class HouseRobber {
    public static int[] memo;

    public static int robMemo(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int sum1 = findSum(nums, 0, memo);
        int sum2 = findSum(nums, 1, memo);
        return Math.max(sum1, sum2);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2};
        System.out.println(robMemo(nums));
    }

    public static int findSum(int[] nums, int index, int[] memo) {
        if (nums.length <= index) {
            return 0;
        }
        if (memo[index] > -1) {
            return memo[index];
        }
        int sum1 = findSum(nums, index + 2, memo);
        int sum2 = findSum(nums, index + 3, memo);
        int max = Math.max(sum1, sum2);
        memo[index] = max + nums[index];
        return max + nums[index];
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];


        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }
}
