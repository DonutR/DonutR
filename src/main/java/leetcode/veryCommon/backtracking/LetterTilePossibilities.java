package leetcode.veryCommon.backtracking;

import java.util.Arrays;

public class LetterTilePossibilities {
    public static int ct = 0;

    public int numTilePossibilities(String tiles) {
        ct = 0;
        char[] inp = tiles.toCharArray();
        Arrays.sort(inp);
        boolean[] isUsedNum = new boolean[inp.length];
        recFun(inp, isUsedNum);
        return ct - 1;
    }

    public static void recFun(char[] inp, boolean[] isUsedNum) {
        ct++;
        char prev = (char) (inp[0] - 1);
        for (int i = 0; i < inp.length; i++) {
            if (inp[i] != prev && !isUsedNum[i]) {
                isUsedNum[i] = true;
                prev = inp[i];
                recFun(inp, isUsedNum);
                isUsedNum[i] = false;
            }
        }
    }

}
