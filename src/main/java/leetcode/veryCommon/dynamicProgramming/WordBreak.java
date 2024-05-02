package leetcode.veryCommon.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WordBreak {
    HashSet<String> dict = new HashSet<>();

    //DFS Approach
    public boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>();
        wordDict.stream().forEach(w -> dict.add(w));
        return helper(s);
    }

    public boolean helper(String s) {
        if (dict.contains(s)) return true;
        int n = s.length();
        for (int i = 1; i < n; i++) {
            String left = s.substring(0, i);
            String right = s.substring(i, n);
            Boolean rightRes = helper(right);
            Boolean leftRes = helper(left);
            if (leftRes && rightRes)
                return leftRes && rightRes;
        }
        return false;
    }
}
