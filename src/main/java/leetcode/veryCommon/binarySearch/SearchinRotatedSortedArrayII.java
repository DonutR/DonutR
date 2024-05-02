package leetcode.veryCommon.binarySearch;

import java.util.Arrays;

public class SearchinRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[start] <= nums[mid] && nums[mid] <= nums[end]) {
                //Normal Array scenario
                if (nums[start] == nums[mid] && nums[mid] == nums[end])
                    start++;
                else if (nums[mid] < target) start = mid + 1;
                else end = mid - 1;
            } else {
                //Pivoted Array scenario
                if (nums[mid] <= nums[end]) {
                    if (target >= nums[mid] && target <= nums[end]) start = mid + 1;
                    else end = mid - 1;
                } else {
                    if (target >= nums[start] && target <= nums[mid]) end = mid - 1;
                    else start = mid + 1;
                }
            }
        }
        return false;
    }
}
