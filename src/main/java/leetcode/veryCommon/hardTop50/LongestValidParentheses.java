package leetcode.veryCommon.hardTop50;

import java.util.Stack;

public class LongestValidParentheses {
    public int longestValidParentheses1(String s) {
        int max = 0;
        Stack<Integer> stk = new Stack<>();
        stk.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                stk.push(i);
            else {
                stk.pop();
                if (stk.isEmpty())
                    stk.push(i);
                else
                    max = Math.max(max, i - stk.peek());
            }
        }
        return max;
    }

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            if (left == right) {
                max = Math.max(max, right * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;
            if (left == right) {
                max = Math.max(max, left * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }
}
