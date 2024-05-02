package leetcode.veryCommon.random;

import java.util.Arrays;

//Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
//
//        Example 1:
//
//        Input: nums = [1, 5, 1, 1, 6, 4]
//[1, 5, 1, 6, 4, 1]
//        Output: One possible answer is [1, 4, 1, 5, 1, 6].
//        Example 2:
//
//        Input: nums = [1, 3, 2, 2, 3, 1]
//        Output: One possible answer is [2, 3, 1, 3, 1, 2].
//        Note:
//        You may assume all input has valid answer.
//
//        Follow Up:
//        Can you do it in O(n) time and/or in-place with O(1) extra space?
//111456 --> 1 4 1 5 1 6
//1114567 --> 1 4 1 5 1 7 6

//1, 3, 2, 2, 3, 1() --> 1, 1, 2, 2, 3, 3(1,5) -> 1, 3, 2, 2, 3, 1(3,4) -> 1, 3, 2, 3, 2, 1(5,3) -> 1, 3, 2, 3, 1, 2
//1, 5, 1, 1, 6, 4() --> 1, 1, 1, 4, 5, 6(1,5) -> 1, 6, 1, 4, 5, 1(3,4) -> 1, 6, 1, 5, 4, 1(5,3)
//1, 5, 1, 1, 6, 4, 7() --> 1, 1, 1, 4, 5, 6, 7(1,6) -> 1, 7, 1, 4, 5, 6, 1(3,5) -> 1, 7, 1, 6, 5, 4, 1(5,4)
//1, 5, 1, 1, 6, 4, 7() --> 1, 1, 1, 4, 5, 6, 7 -->     1, 7, 1, 1, 4, 5, 6(1,6) -> 1, 7, 1, 6, 1, 4, 5(3,5) -> 1, 7, 1, 6, 1, 5, 4(5,4)


public class WiggleSortII {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, 3, 1};
        wiggleSort(nums);
    }

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[nums.length - 1];
            for (int k = nums.length - 2; k > i; k--) {
                nums[k + 1] = nums[k];
            }
            if (i + 1 < nums.length)
                nums[i + 1] = tmp;
        }
    }

}
