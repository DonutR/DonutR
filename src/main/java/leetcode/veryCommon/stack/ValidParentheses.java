package leetcode.veryCommon.stack;

import java.util.*;

public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("(]"));
    }

    public static boolean isValid(String s) {
        HashMap<String, String> openB = new HashMap<>();
        openB.put("(", ")");
        openB.put("[", "]");
        openB.put("{", "}");

        Stack<String> tempStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (openB.containsKey(s.charAt(i) + "")) {
                tempStack.push(s.charAt(i) + "");
            } else {
                if (tempStack.isEmpty())
                    return false;
                String tmp = tempStack.pop();
                if (!openB.get(tmp).equals(s.charAt(i) + ""))
                    return false;
            }
        }
        if (tempStack.isEmpty())
            return true;
        else
            return false;
    }

}
