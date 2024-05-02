package leetcode.veryCommon.stack;

import java.util.Stack;

public class ReverseSubstringsBetweenEachPairofParentheses {
    public String reverseParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stk.push(i);
            else if (s.charAt(i) == ')') {
                int st = stk.pop() + 1;
                StringBuffer sbr = new StringBuffer(s.substring(st, i));
                s = s.substring(0, st) + sbr.reverse().toString() + s.substring(i, s.length());
            }
        }
        return s.replaceAll("\\(","").replaceAll("\\)","");
    }
}
