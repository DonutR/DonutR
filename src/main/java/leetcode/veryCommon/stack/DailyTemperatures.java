package leetcode.veryCommon.stack;

import java.util.Stack;

public class DailyTemperatures {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; ++i) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int sIdx = stack.pop();
                res[sIdx] = i - sIdx;
            }
            stack.push(i);
        }
        return res;
    }
}
