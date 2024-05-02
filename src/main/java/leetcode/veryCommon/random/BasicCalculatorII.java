package leetcode.veryCommon.random;

//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
//
//        Example 1:
//
//        Input: "3+2*2"
//        Output: 7
//        Example 2:
//
//        Input: " 3/2 "
//        Output: 1
//        Example 3:
//
//        Input: " 3+5 / 2 "
//        Output: 5
//        Note:
//
//        You may assume that the given expression is always valid.
//        Do not use the eval built-in library function.

import java.util.*;
import java.util.stream.Collectors;

public class BasicCalculatorII {
    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
    }


    public static int calculate1(String s) {
        List<String> splitExpr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if ((c >= 48 && c <= 57) || c == 32) {
                sb.append(c);
            } else {
                splitExpr.add(sb.toString().trim());
                sb = new StringBuilder();
                splitExpr.add(String.valueOf(c));
            }
        }
        splitExpr.add(sb.toString().trim());
        System.out.println(splitExpr);
        String[] splitArray = new String[splitExpr.size()];
        splitExpr.toArray(splitArray);

        for (int i = 1; i < splitArray.length; i += 2) {
            int a = Integer.parseInt(splitArray[i - 1]);
            String expr = splitArray[i];
            int c = Integer.parseInt(splitArray[i + 1]);

            if (expr.equals("/") || expr.equals("*")) {
                if (expr.equals("/") && a != 0)
                    a = a / c;
                else if (expr.equals("/") && a == 0)
                    a = 0;
                else if (expr.equals("*"))
                    a = a * c;

                splitArray[i - 1] = "";
                splitArray[i] = "";
                splitArray[i + 1] = a + "";
            }
        }
        int out = 0;
        List<String> tmpList = Arrays.stream(splitArray).filter(a -> !a.equals("")).collect(Collectors.toList());
        if (tmpList.size() > 0) {
            Iterator<String> it = tmpList.listIterator();
            out = Integer.parseInt(it.next());
            while (it.hasNext()) {
                String exp = it.next();
                if (exp.equals("+"))
                    out = out + Integer.parseInt(it.next());
                else if (exp.equals("-"))
                    out = out - Integer.parseInt(it.next());
            }
        }
        return out;
    }

    public static int calculate(String s) {
        s = s.replaceAll(" ", "");
        List<String> expr = new ArrayList<>();
        int i = 0, j = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                expr.add(s.substring(j, i));
                expr.add(c + "");
                j = i + 1;
            }
        }
        expr.add(s.substring(j, s.length()));
        for (int k = 0; k < expr.size(); ) {
            if (expr.get(k).equals("*") || expr.get(k).equals("/")) {
                calculateExpr(expr,k);
            }else k++;
        }
        for (int k = 0; k < expr.size(); ) {
            if (expr.get(k).equals("+") || expr.get(k).equals("-")) {
                calculateExpr(expr,k);
            }else k++;
        }
        return Integer.parseInt(expr.get(0));
    }

    public static void calculateExpr(List<String> expr, int k) {
        if (expr.get(k).equals("*") || expr.get(k).equals("/")) {
            int x = 0, a = Integer.parseInt(expr.get(k - 1)), b = Integer.parseInt(expr.get(k + 1));
            if (expr.get(k).equals("*"))
                x = a * b;
            else if (expr.get(k).equals("/"))
                x = a / b;
            else if (expr.get(k).equals("+"))
                x = a + b;
            else
                x = a - b;
            expr.remove(k);
            expr.remove(k + 1);
            expr.set(k - 1, x + "");
        }
    }
}
