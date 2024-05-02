package leetcode.veryCommon.binarySearch;

import java.util.Arrays;

public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        int lo = (Arrays.stream(piles).sum() - 1 )/ H + 1;
        int hi = Arrays.stream(piles).max().getAsInt();
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1;
            else hi = mi;
        }
        return lo;
    }

    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p : piles) {
            time += (p - 1) / K + 1;
        }
        return time <= H;
    }
}
