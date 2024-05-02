package leetcode.veryCommon.hardTop50;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * <p>
 * Find next greater or equal element and min
 */
//DP or Stack
//Think about stack approach very interesting

public class TrappingRainWater {
    public int trap(int[] height) {
        int l = height.length;
        if (l == 0)
            return 0;
        int[] nextMaxE = new int[l];
        int[] prevMaxE = new int[l];

        prevMaxE[0] = height[0];
        nextMaxE[l - 1] = height[l - 1];

        for (int i = 1; i < l; i++) {
            prevMaxE[i] = Math.max(prevMaxE[i - 1], height[i]);
        }

        for (int i = l - 2; i >= 0; i--) {
            nextMaxE[i] = Math.max(nextMaxE[i + 1], height[i]);
        }

        int out = 0;
        for (int i = 1; i < l - 1; i++) {
            out += Math.min(prevMaxE[i], nextMaxE[i]) - height[i];
        }
        return out;
    }
}