package leetcode.veryCommon.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> out = new ArrayList<>();
        int anLen = p.length();
        if (anLen > s.length())
            return out;
        int[] pCtArr = new int[26];
        for (char c : p.toCharArray()) {
            pCtArr[c - 97] = pCtArr[c - 97] + 1;
        }
        int[] sCtArr = new int[26];

        for (int i = 0; i < anLen; i++) {
            sCtArr[s.charAt(i) - 97] = sCtArr[s.charAt(i) - 97] + 1;
        }

        for (int i = 0; i + anLen < s.length(); i++) {
            if (Arrays.equals(sCtArr, pCtArr)) {
                out.add(i);
            }
            sCtArr[s.charAt(i) - 97] = sCtArr[s.charAt(i) - 97] - 1;
            sCtArr[s.charAt(i + anLen) - 97] = sCtArr[s.charAt(i + anLen) - 97] + 1;
        }
        if (Arrays.equals(sCtArr, pCtArr)) {
            out.add(s.length() - anLen);
        }
        return out;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> out = new ArrayList<>();
        HashMap<Character, Integer> pMap = new HashMap<>();
        p.chars().forEach(c -> pMap.put((char) c, pMap.getOrDefault((char) c, 0) + 1));
        int len = p.length();
        int i = 0, j = 0;
        for (; i < s.length(); i++) {
            char cI = s.charAt(i);
            if (i - j == len) {
                out.add(j);
                pMap.put(s.charAt(j), 1);
                j++;
            }
            if (!pMap.containsKey(cI)) {
                j = i + 1;
                p.chars().forEach(c -> pMap.put((char) c, pMap.getOrDefault((char) c, 0) + 1));
            } else if (pMap.get(cI) == 0) {
                while (s.charAt(j) != cI) {
                    pMap.put(s.charAt(j), pMap.get(s.charAt(j)) + 1);
                    j++;
                }
                j++;
            } else
                pMap.put(cI, pMap.get(cI) - 1);
        }
        if (i - j == len) out.add(j);
        return out;
    }
}
