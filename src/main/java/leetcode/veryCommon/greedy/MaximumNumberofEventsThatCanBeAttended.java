package leetcode.veryCommon.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberofEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int i = 0, n = events.length, res = 0;
        PriorityQueue<Integer> closestEndDay = new PriorityQueue<>();
        for (int d = 1; d <= 100000; d++) {
            while (!closestEndDay.isEmpty() && closestEndDay.peek() < d)
                closestEndDay.poll();
            while (i < n && events[i][0] == d)
                closestEndDay.add(events[i++][1]);
            if (!closestEndDay.isEmpty()) {
                closestEndDay.poll();
                res++;
            }
        }
        return res;
    }
}
