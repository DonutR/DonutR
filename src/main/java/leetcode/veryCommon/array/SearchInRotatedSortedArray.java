package leetcode.veryCommon.array;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int pivot = 0;
        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] > nums[i + 1]) {
                pivot = i + 1;
                break;
            }
        }
        int ans = -1;
        ans = binarySearch(nums, pivot, nums.length - 1, target);
        if (ans == -1)
            ans = binarySearch(nums, 0, pivot - 1, target);
        return ans;
    }

    public int binarySearch(int[] arr, int l, int r, int t) {
        if (l <= r) {
            int mid = (r + l) / 2;
            if (arr[mid] == t)
                return mid;

            if (arr[mid] > t)
                return binarySearch(arr, l, mid - 1, t);
            return binarySearch(arr, mid + 1, r, t);
        }
        return -1;
    }
}
