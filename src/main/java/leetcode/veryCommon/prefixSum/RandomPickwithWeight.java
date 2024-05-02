package leetcode.veryCommon.prefixSum;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

public class RandomPickwithWeight {
    class Solution {

        TreeMap<Integer, Integer> prefixSum = new TreeMap<>();
        int totalSum = 0;
        Random r = new Random();

        public Solution(int[] w) {
            prefixSum.put(0, 0);
            for (int i = 0; i < w.length; i++) {
                totalSum += w[i];
                prefixSum.put(totalSum, i);
            }
        }

        public int pickIndex() {
            int target = r.nextInt(totalSum + 1);
            return prefixSum.ceilingEntry(target).getValue();
        }
    }
}
