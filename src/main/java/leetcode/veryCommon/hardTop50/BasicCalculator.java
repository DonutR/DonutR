package leetcode.veryCommon.hardTop50;

import java.util.*;
import java.util.stream.Collectors;

public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("1 + 1"));

    }

    public static int calculate(String s) {
        s = s.replaceAll(" ", "");
        List<String> expr = new ArrayList<>();
        int i = 0, j = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == ')' || c == '+' || c == '-') {
                if (j < i) expr.add(s.substring(j, i));
                expr.add(s.substring(i, i + 1));
                j = i + 1;
            }
        }
        if (j < i) expr.add(s.substring(j, i));

        Stack<String> stk = new Stack<>();
        for (String ss : expr) {
            if (!ss.equals(")"))
                stk.push(ss);
            else {
                ArrayList<String> simpleExpr = new ArrayList<>();
                while (!stk.peek().equals("("))
                    simpleExpr.add(stk.pop());
                stk.pop();
                Collections.reverse(simpleExpr);
                stk.push(evalSimpleExpr(simpleExpr) + "");
            }
        }
        return evalSimpleExpr(stk.subList(0, stk.size()));
    }

    public static int evalSimpleExpr(List<String> expr) {
        int a = Integer.parseInt(expr.get(0));
        for (int i = 1; i < expr.size(); i += 2) {
            int b = Integer.parseInt(expr.get(i + 1));
            if (expr.get(i).equals("+"))
                a = a + b;
            else a = a - b;
        }
        return a;
    }
}
