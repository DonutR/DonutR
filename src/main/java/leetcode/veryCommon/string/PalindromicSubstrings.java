package leetcode.veryCommon.string;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int ans = 0, N = s.length();
        for (int center = 0; center < N; center++) {
            for (int j = 0; j < 2; j++) {
                int left = center;
                int right = center + j;
                while (left >= 0 && right < N && s.charAt(left) == s.charAt(right)) {
                    ans++;
                    left--;
                    right++;
                }
            }
        }
        return ans;
    }
}
