package leetcode.veryCommon.heap;

import java.util.*;

public class DistantBarcodes {
    //1,2,3,4,5
    //n=4->2
    //1,2,3,4
    //n=3 -> 1
    public int[] rearrangeBarcodes(int[] barcodes) {
        Arrays.sort(barcodes);
        List<Integer> output = new ArrayList<>();
        int n = barcodes.length - 1;
        for (int i = 0; i <= n / 2; i++) {
            output.add(barcodes[i]);
            if (n / 2 + i + 1 <= n) output.add(barcodes[n / 2 + i + 1]);
        }
        return output.stream().mapToInt(Integer::intValue).toArray();
    }
}
