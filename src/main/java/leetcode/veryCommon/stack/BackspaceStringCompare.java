package leetcode.veryCommon.stack;

import java.util.Stack;
import java.util.stream.Collectors;

public class BackspaceStringCompare {
    public boolean backspaceCompare1(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1, back;
        while (true) {
            back = 0;
            while (i >= 0 && (back > 0 || S.charAt(i) == '#')) {
                back += S.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            back = 0;
            while (j >= 0 && (back > 0 || T.charAt(j) == '#')) {
                back += T.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else {
                break;
            }
        }
        return i == -1 && j == -1;
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stack = new Stack<>();
        for (Character c : S.toCharArray())
            if (c == '#') {
                if (!stack.isEmpty()) stack.pop();
            } else stack.push(c);
        String sFinal = stack.stream().map(String::valueOf).collect(Collectors.joining());
        stack.clear();
        for (Character c : T.toCharArray())
            if (c == '#') {
                if (!stack.isEmpty()) stack.pop();
            } else stack.push(c);
        String tFinal = stack.stream().map(String::valueOf).collect(Collectors.joining());
        return sFinal.equals(tFinal);
    }
}
