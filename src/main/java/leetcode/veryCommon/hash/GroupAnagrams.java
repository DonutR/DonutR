package leetcode.veryCommon.hash;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> mp = new HashMap<>();
        for (String a : strs) {
            char[] ca = a.toCharArray();
            Arrays.sort(ca);
            String key = new String(ca);
            List<String> tmp = mp.getOrDefault(key, new LinkedList<>());
            tmp.add(a);
            mp.put(key, tmp);
        }
        return new ArrayList<>(mp.values());
    }
}
