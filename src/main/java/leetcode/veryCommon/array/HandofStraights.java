package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class HandofStraights {
    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> ctMp = new TreeMap<>();
        Arrays.stream(hand).forEach(i -> ctMp.put(i, ctMp.getOrDefault(i, 0) + 1));
        for (int i : ctMp.keySet()) {
            System.out.println(i);
            if (ctMp.get(i) > 0) {
                for (int k = 1; k < W - 1; k++) {
                    int newCd = i + k;
                    System.out.println(newCd);
                    if (ctMp.getOrDefault(newCd, 0) < ctMp.get(i)) return false;
                    ctMp.put(newCd, ctMp.get(newCd) - ctMp.get(i));
                }
            }
        }
        return true;
    }
}
