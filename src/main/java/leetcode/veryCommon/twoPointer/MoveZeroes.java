package leetcode.veryCommon.twoPointer;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int zSt = -1;
        for (int i = 0; i < nums.length; i++) {
            if (zSt < 0 && nums[i] == 0)
                zSt = i;
            else if (zSt >= 0 && nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = 0;
                nums[zSt] = tmp;
                zSt++;
            }
        }
    }
}
