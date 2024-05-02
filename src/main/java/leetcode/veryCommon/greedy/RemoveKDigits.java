package leetcode.veryCommon.greedy;

import java.util.Stack;
import java.util.stream.Collectors;

public class RemoveKDigits {
    public static void main(String[] args) {
        System.out.println(removeKdigits("10", 2));
    }

    //The first algorithm is straight-forward. Let's think about the simplest case: how to remove 1 digit from the number so that the new number is the smallest possible？ Well, one can simply scan from left to right, and remove the first "peak" digit; the peak digit is larger than its right neighbor. One can repeat this procedure k times, and obtain the first algorithm:
    public static String removeKdigits1(String num, int k) {
        Stack<Integer> stk = new Stack<>();
        stk.push(num.charAt(0) - 48);
        int i = 1;
        for (int ct = 0; i < num.length() && ct < k; i++) {
            while (!stk.isEmpty() && stk.peek() > num.charAt(i) - 48 && ct < k) {
                stk.pop();
                ct++;
            }
            stk.push(num.charAt(i) - 48);
        }
        System.out.println(stk);
        String intOut = stk.stream().map(String::valueOf).collect(Collectors.joining()) + num.substring(i);
        intOut = intOut.length() >= (num.length() - k) ? intOut.substring(0, (num.length() - k)) : intOut;

        char[] arr = intOut.toCharArray();
        int j = 0;
        for (; j < arr.length; j++)
            if (arr[j] != '0')
                break;
        intOut = intOut.substring(j);
        intOut = (intOut.length() == 0 ? "0" : intOut);
        return intOut;
    }

    public static String removeKdigits(String num, int k) {
        Stack<Character> stk = new Stack<>();
        for (Character c : num.toCharArray()) {
            while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                stk.pop();
                k--;
            }
            if (!stk.isEmpty() || c != '0') stk.push(c);
        }
        String out = stk.stream().map(c -> c + "").collect(Collectors.joining());
        if (out.length() - k >= 0) out = out.substring(0, out.length() - k);
        return out.equals("") ? "0" : out;
    }
}
