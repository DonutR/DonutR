package leetcode.veryCommon.stack;

import java.util.*;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> stack = new Stack<>();
        stack.add(Integer.parseInt(tokens[0]));
        for (int i = 1; i < tokens.length; i++) {
            if (operators.contains(tokens[i])) {
                int b = stack.pop();
                int a = stack.pop();
                int c = 0;
                if (tokens[i].equals("+")) c = a + b;
                else if (tokens[i].equals("-")) c = a - b;
                else if (tokens[i].equals("*")) c = a * b;
                else c = a / b;
                stack.push(c);
            } else stack.push(Integer.parseInt(tokens[i]));
        }
        return stack.pop();
    }
}
