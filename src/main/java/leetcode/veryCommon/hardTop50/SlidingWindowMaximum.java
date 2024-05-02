package leetcode.veryCommon.hardTop50;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new LinkedList<>();
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!q.isEmpty() && q.peek() < i - k + 1) q.poll();
            //if (!q.isEmpty() && q.size() == k) q.poll(); --> This wont work as we are keeping a Queue with values in increasing order from tail to head, head will have the largest value.
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i])
                q.pollLast();
            q.add(i);
            if (i >= k - 1)
                out.add(nums[q.peek()]);
        }
        return out.stream().mapToInt(a -> a).toArray();
    }
}