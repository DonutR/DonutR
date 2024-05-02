package leetcode.veryCommon.hash;

import java.util.HashMap;

public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> tmpSet = new HashMap<>();
        for (int a : A) {
            for (int b : A) {
                int sm = a + b;
                tmpSet.put(sm, tmpSet.getOrDefault(sm, 0) + 1);
            }
        }
        System.out.println(tmpSet);
        int out = 0;
        for (int c : C) {
            for (int d : D) {
                out += tmpSet.getOrDefault(-1 * (c + d), 0);
            }
        }
        return out;
    }
}
