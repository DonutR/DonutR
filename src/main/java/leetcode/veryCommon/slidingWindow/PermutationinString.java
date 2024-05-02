package leetcode.veryCommon.slidingWindow;

import java.util.Arrays;

public class PermutationinString {
    public boolean checkInclusion(String s1, String s2) {
        int[] charCt = new int[26];
        for (int i = 0; i < s1.length(); i++)
            charCt[s1.charAt(i) - 97]++;

        int[] charCtWindow = new int[26];
        int windowSize = 0;
        for (int i = 0, j = 0; j < s2.length(); ) {
            int cj = s2.charAt(j) - 97;
            int ci = s2.charAt(i) - 97;
            if (charCt[cj] > 0) {
                windowSize++;
                charCtWindow[cj]++;
                if (windowSize == s1.length()) {
                    Arrays.stream(charCtWindow).forEach(a -> System.out.print(" " + a));
                    System.out.println();
                    if (Arrays.equals(charCt, charCtWindow))
                        return true;
                    else {
                        windowSize--;
                        charCtWindow[ci] = Math.max(0, charCtWindow[ci] - 1);
                        i++;
                    }
                }
                j++;
            } else {
                i = ++j;
                Arrays.fill(charCtWindow, 0);
            }
        }
        return false;
    }
}
