package leetcode.veryCommon.stack;

import java.util.Stack;

public class ScoreofParentheses {
    public int scoreOfParentheses(String S) {
        Stack<String> stk = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(')
                stk.push("(");
            else {
                int sm = 0;
                while (!stk.isEmpty() && stk.peek().charAt(0) != '(')
                    sm += Integer.parseInt(stk.pop());
                if (sm == 0) {
                    stk.pop();
                    stk.push("1");
                } else {
                    stk.pop();
                    stk.push(2 * sm + "");
                }
            }
        }
        return stk.stream().mapToInt(a->Integer.parseInt(a)).sum();
    }
}
