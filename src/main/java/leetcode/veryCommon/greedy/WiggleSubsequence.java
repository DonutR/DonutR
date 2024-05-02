package leetcode.veryCommon.greedy;

public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int preDiff = nums[1] - nums[0], out = preDiff != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int curDiff = nums[i] - nums[i - 1];
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                out++;
                preDiff = curDiff;
            }
        }
        return out;
    }
}
