package leetcode.veryCommon.random;

import java.util.HashMap;
import java.util.stream.IntStream;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] out = twoSum(nums, target);
        System.out.println(out[0] + " " + out[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numIdxMap = new HashMap<>();
        IntStream.range(0, nums.length).forEach(i -> numIdxMap.put(nums[i], i));
        int[] out = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int compliment = target - nums[i];
            if (numIdxMap.containsKey(compliment) && numIdxMap.get(compliment) != i) {
                return new int[]{numIdxMap.get(compliment), i};
            }
        }
        return null;
    }


}
