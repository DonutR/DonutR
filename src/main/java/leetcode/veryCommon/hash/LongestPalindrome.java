package leetcode.veryCommon.hash;

import java.util.HashMap;

public class LongestPalindrome {
    public int longestPalindrome1(String s) {
        HashMap<Character, Integer> ctMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
            ctMap.put(s.charAt(i), ctMap.getOrDefault(s.charAt(i), 0) + 1);
        int ans = ctMap.entrySet()
                .stream()
                .map(es -> es.getValue() / 2 * 2)
                .reduce(0, (a, b) -> Integer.sum(a, b)).intValue();
        long oddMax = ctMap.entrySet()
                .stream()
                .filter(es -> es.getValue() % 2 != 0)
                .count();
        ans += oddMax > 0 ? 1 : 0;
        return ans;
    }


    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray())
            count[c]++;

        int ans = 0;
        int oddCt = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1)
                oddCt = 1;
        }
        return ans + oddCt;
    }
}
