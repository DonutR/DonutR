package leetcode.veryCommon.greedy;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        Stack<Character> stk = new Stack<>();
        HashSet<Character> containSet = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (!stk.isEmpty() && stk.peek() >= c && map.get(stk.peek()) > 0 && !containSet.contains(c)) {
                containSet.remove(stk.pop());
            }
            if (!containSet.contains(c)) {
                stk.push(c);
                containSet.add(c);
            }
            map.put(c, map.get(c) - 1);
        }
        return stk.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
