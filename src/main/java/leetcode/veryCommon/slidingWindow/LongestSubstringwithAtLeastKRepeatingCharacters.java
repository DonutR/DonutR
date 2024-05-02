package leetcode.veryCommon.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringwithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(longestSubstring("ababacb", 3));
    }

    public static int longestSubstring(String s, int k) {
        HashSet<Character> uniqChar = new HashSet<>();
        // get the maximum number of unique letters in the string s
        for (char c : s.toCharArray())
            uniqChar.add(c);
        int maxUniqChar = uniqChar.size(), result = 0;
        for (int currUniqChar = 1; currUniqChar <= maxUniqChar; currUniqChar++) {
            int windowStart = 0, windowEnd = 0, uniqCharCt = 0, uniqCharWithKCt = 0;
            // reset countMap
            HashMap<Character, Integer> countMap = new HashMap<>();
            while (windowEnd < s.length()) {
                // expand the sliding window
                if (uniqCharCt <= currUniqChar) {
                    char c = s.charAt(windowEnd);
                    if (!countMap.containsKey(c) || countMap.get(c) == 0) uniqCharCt++;
                    countMap.put(c, countMap.getOrDefault(c, 0) + 1);
                    if (countMap.get(c) == k) uniqCharWithKCt++;
                    windowEnd++;
                // shrink the sliding window
                } else {
                    char c = s.charAt(windowStart);
                    if (countMap.get(c) == k) uniqCharWithKCt--;
                    countMap.put(c, countMap.getOrDefault(c, 0) - 1);
                    if (!countMap.containsKey(c) || countMap.get(c) == 0) uniqCharCt--;
                    windowStart++;
                }
                if (uniqCharCt == currUniqChar && uniqCharWithKCt == currUniqChar)
                    result = Math.max(windowEnd - windowStart, result);
            }
        }
        return result;
    }
}
