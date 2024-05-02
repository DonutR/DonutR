package leetcode.veryCommon.twoPointer;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int i = 0, j = len - 1;
        int sm = 0;
        int min = Integer.MAX_VALUE;
        int out = 0;
        for (; i < j; ) {
            sm = nums[i] + nums[j];
            if (sm > target) j--;
            else i++;
            for (int k = 0; k < len; k++) {
                if (k == i || k == j)
                    continue;
                int diff = Math.abs(target - (sm + nums[k]));
                System.out.println(diff);
                if (min > diff) {
                    min = diff;
                    out = sm + nums[k];
                }
            }
        }
        return out;
    }
}
