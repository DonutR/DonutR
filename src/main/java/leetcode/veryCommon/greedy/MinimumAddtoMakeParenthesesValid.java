package leetcode.veryCommon.greedy;

import java.util.Stack;

public class MinimumAddtoMakeParenthesesValid {
    public int minAddToMakeValid1(String S) {
        int ans = 0, bal = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                bal++;
            } else {
                bal--;
                if (bal < 0) {
                    ans++;
                    bal = 0;
                }
            }
        }
        return ans + bal;
    }

    public int minAddToMakeValid(String S) {
        Stack<Character> stk = new Stack<>();
        for (char c : S.toCharArray()) {
            if (!stk.isEmpty() && stk.peek() == '(' && c == ')')
                stk.pop();
            else
                stk.push(c);
        }
        return stk.size();
    }
}
