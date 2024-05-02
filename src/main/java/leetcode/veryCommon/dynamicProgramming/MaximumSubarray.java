package leetcode.veryCommon.dynamicProgramming;

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] inp = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    }

        public int maxSubArray(int[] nums) {
            int sum = 0, out = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                out = Math.max(out, sum);
                if (sum < 0)
                    sum = 0;
            }
            return out;
        }
}
