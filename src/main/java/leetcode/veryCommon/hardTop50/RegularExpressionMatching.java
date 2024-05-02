package leetcode.veryCommon.hardTop50;

public class RegularExpressionMatching {
    Boolean[][] memo;

    public boolean isMatch(String text, String pattern) {
        memo = new Boolean[text.length() + 1][pattern.length() + 1];
        return isMatchRec(text, pattern, 0, 0);
    }

    public boolean isMatchRec(String text, String pattern, int textIdx, int ptnIdx) {
        if (memo[textIdx][ptnIdx] != null) return memo[textIdx][ptnIdx];

        if (pattern.isEmpty()) return text.isEmpty();

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            boolean wildCardRes = isMatchRec(text, pattern.substring(2), textIdx, ptnIdx + 2);
            int repCt = 0;
            while (!wildCardRes && repCt < text.length() &&
                    (text.charAt(repCt) == pattern.charAt(0) || '.' == pattern.charAt(0))) {
                repCt++;
                wildCardRes = isMatchRec(text.substring(repCt), pattern, textIdx + repCt, ptnIdx);
            }
            memo[textIdx][ptnIdx] = wildCardRes;
            return wildCardRes;
        } else {
            boolean first_match = (!text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
            boolean wildCardRes = first_match && isMatchRec(text.substring(1), pattern.substring(1), textIdx + 1, ptnIdx + 1);
            memo[textIdx][ptnIdx] = wildCardRes;
            return wildCardRes;
        }
    }
}
