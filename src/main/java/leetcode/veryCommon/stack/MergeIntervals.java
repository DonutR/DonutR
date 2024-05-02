package leetcode.veryCommon.stack;

import java.util.*;
import java.util.stream.Collectors;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> sortList = Arrays
                .stream(intervals)
                .sorted(Comparator.comparing(a -> a[0]))
                .collect(Collectors.toList());

        Stack<int[]> stack = new Stack<>();
        for (int[] el : sortList) {
            if (!stack.isEmpty() && stack.peek()[1] >= el[0]) {
                int[] a = stack.pop();
                int[] tmp = {a[0], Math.max(a[1], el[1])};
                stack.push(tmp);
            } else stack.push(el);
        }
        int[][] out = new int[stack.size()][];
        stack.toArray(out);
        return out;
    }
}
