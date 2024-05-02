package leetcode.veryCommon.stack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bdtgerg191[zzz]c]"));
    }

    public static String decodeString2(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<String> tmpStrBdr = new ArrayList<>();
                while (!stack.peek().equals("[")) {
                    tmpStrBdr.add(stack.pop());
                }
                stack.pop();
                StringBuilder ctSb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek().length() == 1
                        && stack.peek().charAt(0) >= 48 && stack.peek().charAt(0) <= 57)
                    ctSb.append(stack.pop());
                int ct = Integer.parseInt(ctSb.reverse().toString());
                Collections.reverse(tmpStrBdr);
                String tmpStr = tmpStrBdr.stream().collect(Collectors.joining());
                StringBuilder tmpStrBdr2 = new StringBuilder();
                IntStream.range(0, ct).forEach(t -> tmpStrBdr2.append(tmpStr));
                stack.push(tmpStrBdr2.toString());
            } else stack.add(s.charAt(i) + "");
        }
        return stack.stream().collect(Collectors.joining());
    }

    public static String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            Character c = s.charAt(i);
            StringBuilder sb = new StringBuilder();
            if (c == ']') {
                StringBuilder stCombine = new StringBuilder();
                while (stack.peek().chars().allMatch(Character::isAlphabetic))
                    stCombine.insert(0, stack.pop());
                int ct = Integer.parseInt(stack.pop());
                StringBuilder tmpSb = new StringBuilder();
                IntStream.range(0, ct).forEach(t -> tmpSb.append(stCombine.toString()));
                stack.push(tmpSb.toString());
                i++;
            } else if (c == '[') {
                i++;
            } else {
                boolean isDigit = Character.isDigit(s.charAt(i));
                while (i < s.length() && isDigit==Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                }
                stack.add(sb.toString());
                sb = new StringBuilder();
                while (i < s.length() && Character.isAlphabetic(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                }
                stack.add(sb.toString());
            }
        }
        return stack.stream().collect(Collectors.joining());
    }
}
