package leetcode.veryCommon.slidingWindow;

import java.util.Arrays;

public class MinimumSwapstoGroupAll1Together {
    public int minSwaps(int[] data) {
        long oneCt = 0;
        oneCt = Arrays.stream(data).filter(x -> x == 1).count();
        int tmpCt = 0, out = 1_000_00;
        for (int i = 0, j = 0; i < data.length; i++) {
            tmpCt += (data[i] == 0 ? 1 : 0);
            if (i - j == oneCt - 1) {
                out = Math.min(out, tmpCt);
                tmpCt -= data[j] == 0 ? 1 : 0;
                j++;
            }
        }
        return out;
    }
}
