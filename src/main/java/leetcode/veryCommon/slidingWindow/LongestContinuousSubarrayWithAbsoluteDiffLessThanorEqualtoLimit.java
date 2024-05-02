package leetcode.veryCommon.slidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {
    public int longestSubarrayBF(int[] nums, int limit) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int out = 0;
        for
        (int i = 0, j = 0; j < nums.length; ) {
            min = Arrays.stream(nums, i, j + 1).min().getAsInt();
            max = Arrays.stream(nums, i, j + 1).max().getAsInt();
            if (max - min <= limit)
                j++;
            else {
                while (true) {
                    i++;
                    min = Arrays.stream(nums, i, j + 1).min().getAsInt();
                    max = Arrays.stream(nums, i, j + 1).max().getAsInt();
                    if (max - min <= limit)
                        break;
                }
            }
            out = Math.max(out, j - i + 1);
        }
        return out;
    }

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> largeIdx = new LinkedList<>();
        Deque<Integer> smallIdx = new LinkedList<>();
        int max = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (!largeIdx.isEmpty() && nums[i] > nums[largeIdx.peekLast()]) largeIdx.pollLast();
            while (!smallIdx.isEmpty() && nums[i] < nums[smallIdx.peekLast()]) smallIdx.pollLast();
            largeIdx.add(i);
            smallIdx.add(i);

            while (nums[largeIdx.peek()] - nums[smallIdx.peek()] > limit && largeIdx.peek() == i) {
                j = smallIdx.pop() + 1;
            }
            while (nums[largeIdx.peek()] - nums[smallIdx.peek()] > limit && smallIdx.peek() == i) {
                j = largeIdx.pop() + 1;
            }
            max = Math.max(i - j + 1, max);
        }
        return max;
    }
}
