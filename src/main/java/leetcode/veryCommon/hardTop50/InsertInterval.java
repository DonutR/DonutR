package leetcode.veryCommon.hardTop50;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> out = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0] || intervals[i][0] > newInterval[1])
                out.add(intervals[i]);
            else {
                int start = Math.min(intervals[i][0], newInterval[0]);
                int end = newInterval[1];
                while (i + 1 < intervals.length && intervals[i + 1][0] <= end)
                    i++;
                end = Math.max(end, intervals[i][1]);
                out.add(new int[]{start, end});
            }
        }
        if (n == 0 || intervals[0][0] > newInterval[1]) out.addFirst(newInterval);
        else if (n > 0 && intervals[n - 1][1] < newInterval[0]) out.add(newInterval);
        int[][] outArr = new int[out.size()][];
        return out.toArray(outArr);
    }
}
