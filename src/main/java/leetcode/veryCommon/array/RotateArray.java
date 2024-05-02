package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class RotateArray {
    public void rotate2(int[] nums, int k) {
        {
            int ct = nums.length;
            if (ct == 0) {
                nums = null;
                return;
            }
            int actRotate = k % ct;
            actRotate = ct - actRotate;
            LinkedList<Integer> out = new LinkedList<>();
            for (int i = 0; i < actRotate; i++) {
                out.add(nums[i]);
            }
            LinkedList<Integer> out2 = new LinkedList<>();
            for (int i = actRotate; i < ct; i++) {
                out2.add(nums[i]);
            }
            int j = 0;
            for (Integer v : out2) {
                nums[j] = v;
                j++;
            }
            for (Integer v : out) {
                nums[j] = v;
                j++;
            }
        }
    }

    public void rotate3(int[] nums, int k) {
        Stack<Integer> stk = new Stack<>();
        if (k > nums.length) k = nums.length - k;
        int n = nums.length - 1;
        for (int i = n, j = n; i >= 0; i--) {
            if (n - i < k) stk.add(nums[i]);
            else {
                nums[j] = nums[i];
                j--;
            }
        }
        int i = 0;
        while (!stk.isEmpty()) {
            nums[i] = stk.pop();
            i++;
        }
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
