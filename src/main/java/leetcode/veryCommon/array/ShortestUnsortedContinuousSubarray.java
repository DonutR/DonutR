package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 * <p>
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 100, 10, 11, 200, 12, 13, 300};
        System.out.println(findUnsortedSubarray(nums));
    }

    //Sorting
    public static int findUnsortedSubarray1(int[] nums) {
        int[] tmp = nums.clone();
        int start = nums.length, end = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != tmp[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        int len = end - start + 1;
        return len < 0 ? 0 : len;
    }

    //Stack with 2D Line Chart similar to monotinic stack
    //Time Complexity : O(n)
    //Space Complexity : O(n)
    public static int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int l = nums.length, r = -1;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        int len = r - l + 1;
        return len < 0 ? 0 : len;
    }
}
