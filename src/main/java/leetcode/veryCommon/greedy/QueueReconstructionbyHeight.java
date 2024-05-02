package leetcode.veryCommon.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QueueReconstructionbyHeight {
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> out = new LinkedList<>();
        List<int[]> sortinp = Arrays.stream(people).sorted((a, b) -> {
            if (b[0] - a[0] != 0) return b[0] - a[0];
            else return a[1] - b[1];
        }).collect(Collectors.toList());

        for (int[] el : sortinp) {
            out.add(el[1], el);
        }
        int[][] outArr = new int[people.length][];
        return out.toArray(outArr);
    }
}
