package leetcode.veryCommon.dynamicProgramming;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] inp = {2, 3, -2, 4};
        System.out.println(maxProduct(inp));
    }

    public static int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
            minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));

            maxSoFar = tempMax;
            result = Math.max(result, maxSoFar);
        }
        return result;
    }
}
