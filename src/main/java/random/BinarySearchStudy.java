package random;

public class BinarySearchStudy {
    //Binary Search to find a element
    //Binary search to find element less than
    //Binary search to find element greater than

    public static void main(String[] args) {
        int[] inp = new int[]{0, 1, 2, 3, 10, 11, 12};

        for (int i = -1; i < 15; i++)
            System.out.println(i + " : " + searchEqual(inp, i));

        for (int i = -1; i < 15; i++)
            System.out.println(i + " : " + searchLessThanOrEqual(inp, i));

        for (int i = -1; i < 15; i++)
            System.out.println(i + " : " + searchGreaterThanOrEqual(inp, i));
    }

    public static int searchEqual(int[] input, int t) {
        int l = 0, r = input.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int midVal = input[mid];
            if (t > midVal)
                l = mid + 1;
            else if (t < midVal) {
                r = mid - 1;
            } else if (midVal == t)
                return mid;
        }
        return -1;
    }

    public static int searchLessThanOrEqual(int[] input, int t) {
        int l = 0, r = input.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int midVal = input[mid];
            if (t > midVal) {
                ans = mid;
                l = mid + 1;
            } else if (t < midVal)
                r = mid - 1;
            else if (midVal == t)
                return mid;
        }
        return ans;
    }

    public static int searchGreaterThanOrEqual(int[] input, int t) {
        int l = 0, r = input.length - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int midVal = input[mid];
            if (t > midVal)
                l = mid + 1;
            else if (t < midVal) {
                ans = mid;
                r = mid - 1;
            } else if (midVal == t)
                return mid;
        }
        return ans;
    }

    public int missingElement(int[] nums, int k) {
        int l = 0, n = nums.length, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int leftMissingCt = nums[mid] - nums[0] - mid;
            if (k <= leftMissingCt)
                r = mid - 1;
            else if (k > leftMissingCt)
                l = mid + 1;
        }
        int leftMissingCt = nums[r] - nums[0] - l - 1;
        return nums[r] + k - leftMissingCt;
    }

    public class DirectionControlledBinarySearch {
        public int[] searchRange(int[] nums, int target) {
            int[] out = {-1, -1};
            out[0] = searchRange(nums, target, true);
            if (out[0] > -1)
                out[1] = searchRange(nums, target, false);
            return out;
        }

        public int searchRange(int[] nums, int t, boolean isMovingLeft) {
            int l = 0, r = nums.length - 1;
            int mid = 0;
            boolean contains = false;
            while (l <= r) {
                mid = (l + r) / 2;
                if (t == nums[mid])
                    contains = true;
                if (nums[mid] > t || (isMovingLeft && t == nums[mid]))
                    r = mid - 1;
                else if (nums[mid] < t || (!isMovingLeft && t == nums[mid]))
                    l = mid + 1;
            }
            return contains == false ? -1 : isMovingLeft ? l : l - 1;
        }
    }
}
