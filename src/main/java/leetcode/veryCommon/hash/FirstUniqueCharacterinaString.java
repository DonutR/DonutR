package leetcode.veryCommon.hash;

import java.util.HashMap;

public class FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer[]> mp = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer[] tmp = mp.getOrDefault(c, new Integer[]{0, i});
            tmp[0]++;
            mp.put(c, tmp);
        }
        return mp
                .entrySet()
                .stream()
                .filter(es -> es.getValue()[0] == 1)
                .map(c -> c.getValue()[1])
                .min((a, b) -> a - b)
                .orElseGet(() -> -1)
                .intValue();
    }
}
