package leetcode.veryCommon.random;

//Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//
//        Note:
//        The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
//
//        Example 1:
//
//        Input: nums = [1, -1, 5, -2, 3], k = 3
//        Output: 4
//        Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
//        Example 2:
//
//        Input: nums = [-2, -1, 2, 1], k = 1
//        Output: 2
//        Explanation: The subarray [-1, 2] sums to 1 and is the longest.
//        Follow Up:
//        Can you do it in O(n) time?
public class MaximumSizeSubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        maxSubArrayLen(nums, k);
    }

    public static int maxSubArrayLen(int[] nums, int k) {
        int[] min = new int[nums.length];
        int[] max = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                min[i] = (i - 1 >= 0 ? min[i - 1] : 0) + nums[i];
                max[i] = (i - 1 >= 0 ? max[i - 1] : 0);
            } else if (nums[i] >= 0) {
                max[i] = (i - 1 >= 0 ? max[i - 1] : 0) + nums[i];
                min[i] = (i - 1 >= 0 ? min[i - 1] : 0);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(max[i] + " " + min[i]);
            if (Math.abs(max[i] + min[i]) == k) {
                System.out.println(i);
            }
        }
        return 0;
    }
}
