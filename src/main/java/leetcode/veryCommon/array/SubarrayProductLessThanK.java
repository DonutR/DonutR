package leetcode.veryCommon.array;

public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int out = nums[0] < k ? 1 : 0, prod = nums[0];
        int left = 0, right = 1;
        for (; right < nums.length; right++) {
            if (nums[right] < k) out++;
            prod *= nums[right];
            while (prod >= k)
                prod /= nums[left++];
            if (right - left > 0) out += right - left;
        }
        return out;
    }
}
