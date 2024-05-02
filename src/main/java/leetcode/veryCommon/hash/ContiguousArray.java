package leetcode.veryCommon.hash;

import java.util.HashMap;

//Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
//
//        Example 1:
//        Input: [0, 1]
//        Output: 2
//        Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
//        Example 2:
//        Input: [0,1,0]
//        Output: 2
//        Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//        Note: The length of the given binary array will not exceed 50,000.
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int sm = 0;
        int out = 1;
        HashMap<Integer, Integer> index = new HashMap<>();
        index.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sm = sm + (nums[i] == 0 ? -1 : 1);
            if (!index.containsKey(sm))
                index.put(sm, i);
            else out = Math.max(out, i - index.get(sm));
        }
        return out;
    }
}
