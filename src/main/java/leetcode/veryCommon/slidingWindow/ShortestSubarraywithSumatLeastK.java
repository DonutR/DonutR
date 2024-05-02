package leetcode.veryCommon.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestSubarraywithSumatLeastK {
    public int shortestSubarray(int[] nums, int k) {
        int N = nums.length, ans = N + 1;

        long[] P = new long[N + 1];
        for (int i = 0; i < N; ++i)
            P[i + 1] = P[i] + (long) nums[i];

        Deque<Integer> stack = new LinkedList<>();

        for (int y = 0; y < P.length; y++) {
            while (!stack.isEmpty() && P[stack.peekLast()] >= P[y])
                stack.pollLast();
            while (!stack.isEmpty() && P[y] - P[stack.peekFirst()] >= k)
                ans = Math.min(ans, y - stack.pollFirst());
            stack.addLast(y);
        }
        return ans < N + 1 ? ans : -1;
    }
}
