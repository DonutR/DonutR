package leetcode.veryCommon.greedy;

import java.util.Arrays;

public class SetIntersectionSizeAtLeastTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->
                a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int min = intervals[0][1] - 1, max = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            max = Math.max(max, intervals[i][0] + 1);
            System.out.println(intervals[i][0]+", "+intervals[i][1]+"--- "+min + " - " + max);
        }
        return max - min + 1;
    }
}
