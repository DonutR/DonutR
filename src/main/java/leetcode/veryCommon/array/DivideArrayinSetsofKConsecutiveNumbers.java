package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DivideArrayinSetsofKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] nums, int k) {
        HashMap<Integer, Integer> ctMp = new HashMap<>();
        Arrays.stream(nums).forEach(i -> ctMp.put(i, ctMp.getOrDefault(i, 0) + 1));
        for (Integer i : ctMp.keySet().stream().sorted().collect(Collectors.toList())) {
            while (ctMp.get(i) > 0) {
                ctMp.put(i, ctMp.get(i) - 1);
                for (int j = 1; j < k; j++) {
                    int nextNum = i + j;
                    if (ctMp.getOrDefault(nextNum, 0) == 0) return false;
                    else ctMp.put(nextNum, ctMp.get(nextNum) - 1);
                }
            }
        }
        return true;
    }
}
