package leetcode.veryCommon.greedy;

import java.util.*;
import java.util.stream.Collectors;

public class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        HashMap<Character, Integer> mp = new HashMap<>();
        if (a > 0) mp.put('a', a);
        if (b > 0) mp.put('b', b);
        if (c > 0) mp.put('c', c);
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        q.addAll(mp.entrySet());


        List<String> out = new ArrayList<>();
        while (q.size() > 1) {
            Map.Entry<Character, Integer> top = q.poll();
            int n = 1;
            String str1 = "", str2 = "";
            if (top.getValue() > 1) {
                n = 2;
                str1 = (char) top.getKey() + "" + (char) top.getKey() + "";
            } else {
                str1 = (char) top.getKey() + "";
            }

            Map.Entry<Character, Integer> top2 = q.poll();
            str2 = (char) top2.getKey() + "";

            if (out.size() > 0 && out.get(out.size() - 1).contains(top.getKey() + "")) {
                out.add(str2);
                out.add(str1);
            } else {
                out.add(str1);
                out.add(str2);
            }
            top.setValue(top.getValue() - n);
            top2.setValue(top2.getValue() - 1);
            if (top.getValue() > 0)
                q.add(top);
            if (top2.getValue() > 0)
                q.add(top2);
        }
        if (q.size() == 1) {
            Map.Entry<Character, Integer> top = q.poll();
            if (top.getValue() > 1 && out.size() > 0 && !out.get(out.size() - 1).contains(top.getKey() + "")) {
                String str1 = (char) top.getKey() + "" + (char) top.getKey() + "";
                out.add(str1);
            } else out.add((char) top.getKey() + "");
        }
        System.out.println(out);
        return out.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
