package leetcode.veryCommon.hardTop50;

public class BurstBalloons {
    int[] nums;
    int[][] memo;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        this.nums = new int[n + 2];
        for (int i = 1; i <= n; i++)
            this.nums[i] = nums[i - 1];
        this.nums[0] = 1;
        this.nums[n + 1] = 1;
        this.memo = new int[n + 2][n + 2];
        return helper(1, n);
    }

    public int helper(int left, int right) {
        // return maximum if we burst all nums[left]...nums[right], inclusive
        if (left > right)
            return 0;
        // we've already seen this, return from cache
        if (memo[left][right] > 0) {
            return this.memo[left][right];
        }
        // find the last burst one in nums[left]...nums[right]
        int maxGain = 0;
        for (int i = left; i <= right; i++) {
            // nums[i] is the last burst one
            int gain = nums[left - 1] * nums[i] * nums[right + 1];
            // nums[i] is fixed, recursively call left side and right side
            int leftGain = helper(left, i - 1);
            int rightGain = helper(i + 1, right);
            maxGain = Math.max(maxGain, gain + leftGain + rightGain);
        }
        // add to the cache
        this.memo[left][right] = maxGain;
        return maxGain;
    }
}
