package leetcode.veryCommon.slidingWindow;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] charCt = new int[26];
        int ans = Integer.MIN_VALUE;
        int maxDupCt = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            int lenSub = j - i + 1;
            charCt[s.charAt(j) - 65]++;
            maxDupCt = Math.max(maxDupCt, charCt[s.charAt(j) - 65]);
            if (lenSub - maxDupCt - k >= 0) {
                charCt[s.charAt(i) - 65]--;
                i++;
            } else ans = Math.max(ans, lenSub);
        }
        return ans;
    }
}
