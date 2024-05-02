package leetcode.veryCommon.hardTop50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("a", "a"));
    }

    public static String minWindow(String s, String t) {
        HashSet<Character> tSet = new HashSet<>();
        HashMap<Character, Integer> tDict = new HashMap<>();
        HashMap<Character, Integer> refDict = new HashMap<>();


        for (char c : t.toCharArray()) {
            tSet.add(c);
            tDict.put(c, tDict.getOrDefault(c, 0) + 1);
            refDict.put(c, tDict.getOrDefault(c, 0) + 1);
        }

        int minLen = s.length(), maxI = 0, maxJ = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (tSet.contains(c)) {
                tDict.put(c, tDict.getOrDefault(c, 0) - 1);
            }
            boolean dictVal = true;
            for (Map.Entry<Character, Integer> mp : refDict.entrySet()) {
                if (tDict.get(mp.getKey()) > 0) {
                    dictVal = false;
                    break;
                }
            }
            while (dictVal && j < s.length()) {
                if (dictVal && i - j + 1 <= minLen) {
                    minLen = i - j + 1;
                    maxI = i + 1;
                    maxJ = j;
                }
                Character cJ = s.charAt(j);
                if (tSet.contains(cJ)) {
                    tDict.put(cJ, tDict.getOrDefault(cJ, 0) + 1);
                }
                for (Map.Entry<Character, Integer> mp : refDict.entrySet()) {
                    if (tDict.get(mp.getKey()) > 0) {
                        dictVal = false;
                        break;
                    }
                }
                j++;
            }
        }
        return s.substring(maxJ, maxI);
    }
}
