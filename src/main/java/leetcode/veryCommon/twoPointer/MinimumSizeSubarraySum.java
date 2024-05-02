package leetcode.veryCommon.twoPointer;

//Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
//
//        Example:
//
//        Input: s = 7, nums = [2,3,1,2,4,3]
//        Output: 2
//        Explanation: the subarray [4,3] has the minimal length under the problem constraint.
//        Follow up:
//        If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = -1, sm = 0; i < nums.length; i++) {
            sm = sm + nums[i];
            if (sm >= s) {
                while (j <= i) {
                    j++;
                    sm = sm - nums[j];
                    if (sm < s) {
                        sm = sm + nums[j];
                        j--;
                        break;
                    }
                }
                if (min >= i - j)
                    min = i - j;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        int out = nums.length + 1;
        int start = 0, end = 0, sum = 0;
        while (end < nums.length) {
            sum = sum + nums[end];
            while (sum >= s) {
                out = Math.min(out, end - start + 1);
                sum = sum - nums[start];
                start++;
            }
            end++;
        }
        return out > nums.length ? 0 : out;
    }
}
