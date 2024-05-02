package leetcode.veryCommon.greedy;

import java.util.*;

//HEAP + GREEDY

public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(reorganizeString("vvvlo"));
        System.out.println(reorganizeString("aabc"));
        System.out.println(reorganizeString("aaabbc"));
        System.out.println(reorganizeString("aaz"));
        System.out.println(reorganizeString("aazcdeff"));

    }

    public static String reorganizeString2(String S) {
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        q.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Map.Entry<Character, Integer> curChar = q.poll();
            for (int i = 1; i < curChar.getValue(); i++) {
                Map.Entry<Character, Integer> nextChar;
                if (!q.isEmpty()) {
                    nextChar = q.poll();
                    nextChar.setValue(nextChar.getValue() - 1);
                    if (nextChar.getValue() > 0)
                        q.add(nextChar);
                } else return "";
                sb.append(curChar.getKey());
                sb.append(nextChar.getKey());
            }
            sb.append(curChar.getKey());
        }
        return sb.toString();
    }

    public static String reorganizeString(String S) {
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        q.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Map.Entry<Character, Integer> curChar = q.poll();
            if (!q.isEmpty()) {
                Map.Entry<Character, Integer> nextChar = q.poll();
                sb.append(curChar.getKey());
                sb.append(nextChar.getKey());
                if (curChar.getValue() > 1) {
                    curChar.setValue(curChar.getValue() - 1);
                    q.add(curChar);
                }
                if (nextChar.getValue() > 1) {
                    nextChar.setValue(nextChar.getValue() - 1);
                    q.add(nextChar);
                }
            } else if (curChar.getValue()==1 && q.size() == 0)
                sb.append(curChar.getKey());
            else return "";
        }
        return sb.toString();
    }
}
