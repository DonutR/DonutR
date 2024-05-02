package leetcode.veryCommon.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> charIdxmap = new HashMap<>();
        int out = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!charIdxmap.containsKey(c)) {
                charIdxmap.put(c, i);
                out = Math.max(out, i - j + 1);
            } else {
                if (charIdxmap.get(c) > j)
                    j = charIdxmap.get(c);
                out = Math.max(out, i - j);
                charIdxmap.put(c, i);
            }
        }
        return out;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

