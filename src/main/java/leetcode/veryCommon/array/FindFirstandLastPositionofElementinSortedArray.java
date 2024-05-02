package leetcode.veryCommon.array;

public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] out = {-1, -1};
        out[0] = searchRange(nums, target, true);
        if (out[0] > -1)
            out[1] = searchRange(nums, target, false);
        return out;
    }

    public int searchRange(int[] nums, int target, boolean left) {
        int start = 0, end = nums.length - 1;
        int mid = 0;
        boolean contains = false;
        while (start <= end) {
            mid = (end + start) / 2;
            if (target == nums[mid])
                contains = true;
            if (nums[mid] > target || (left && target == nums[mid]))
                end = mid - 1;
            else if (nums[mid] < target || (!left && target == nums[mid]))
                start = mid + 1;
        }
        return contains == false ? -1 : left ? start : start - 1;
    }
}
