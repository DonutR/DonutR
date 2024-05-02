package leetcode.veryCommon.hardTop50;

/*
f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.

f(i, j) = f(i - 1, j - 1)

Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper

f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }

f(i, j - 1) represents insert operation
f(i - 1, j) represents delete operation
f(i - 1, j - 1) represents replace operation
 */
public class EditDistance {
    int[][] memo;

    public int minDistance(String word1, String word2) {
        memo = new int[word1.length() + 1][word2.length() + 1];
        return helper(word1, word2);
    }

    public int helper(String word1, String word2) {
        int a, b, c, res, m = word1.length(), n = word2.length();
        if (word1.length() == 0 && word2.length() == 0)
            return 0;
        if (memo[m][n] > 0)
            return memo[m][n];
        if (word1.length() > 0 && word2.length() > 0 && word1.charAt(0) == word2.charAt(0))
            res = helper(word1.substring(1), word2.substring(1));
        else {
            a = word1.length() > 0 && word2.length() > 0 ? helper(word1.substring(1), word2.substring(1)) : Integer.MAX_VALUE;
            b = word1.length() > 0 ? helper(word1.substring(1), word2) : Integer.MAX_VALUE;
            c = word2.length() > 0 ? helper(word1, word2.substring(1)) : Integer.MAX_VALUE;
            res = Math.min(a, Math.min(b, c)) + 1;
        }
        memo[m][n] = res;
        return res;
    }
}
