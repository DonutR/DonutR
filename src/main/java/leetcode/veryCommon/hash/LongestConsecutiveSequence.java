package leetcode.veryCommon.hash;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        Arrays.stream(nums).forEach(a -> numSet.add(a));
        int maxLen = 0;
        for (int curNum : nums) {
            if (!numSet.contains(curNum - 1)) {
                int curLen = 0;
                while (numSet.contains(curNum + 1)) {
                    curLen++;
                    curNum++;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }
}
