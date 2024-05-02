package leetcode.veryCommon.slidingWindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new LinkedList<>();
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!q.isEmpty() && q.peek() < i - k + 1) q.poll();
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.pollLast();
            q.add(i);
            if (i >= k - 1)
                out.add(nums[q.peek()]);
        }
        return out.stream().mapToInt(a -> a).toArray();
    }
}