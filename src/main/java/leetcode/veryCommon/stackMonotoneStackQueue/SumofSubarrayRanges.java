package leetcode.veryCommon.stackMonotoneStackQueue;

import java.util.Stack;

public class SumofSubarrayRanges {
    public long subArrayRanges(int[] nums) {
        Stack<Integer> s = new Stack<>();
        long res = 0;
        int N = nums.length, i = 0, j = 0, k = 0;
        for (i = 0; i < N; i++) {
            while (!s.isEmpty() && nums[s.peek()] > nums[i]) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res -= (long)nums[j] * (i - j) * (j - k);
            }
            s.push(i);
        }
        while (!s.isEmpty()) {
            j = s.pop();
            k = s.isEmpty() ? -1 : s.peek();
            res -= (long) nums[j] * (i - j) * (j - k);
        }
        s.clear();
        for (i = 0; i < N; i++) {
            while (!s.isEmpty() && nums[s.peek()] < nums[i]) {
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                res += (long)nums[j] * (i - j) * (j - k);
            }
            s.push(i);
        }
        while (!s.isEmpty()) {
            j = s.pop();
            k = s.isEmpty() ? -1 : s.peek();
            res += (long) nums[j] * (i - j) * (j - k);
        }
        return res;
    }
}
