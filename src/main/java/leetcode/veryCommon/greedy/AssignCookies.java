package leetcode.veryCommon.greedy;

import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int out = 0;
        for (int cld = g.length - 1, cky = s.length - 1; cld >= 0 && cky >= 0; cld--) {
            if (g[cld] <= s[cky]) {
                cky--;
                out++;
            }
        }
        return out;
    }
}
