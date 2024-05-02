package leetcode.veryCommon.array;

import java.util.PriorityQueue;

public class BulbSwitcherIII {
    public int numTimesAllBlue1(int[] light) {
        int out = 0;
        if (light[0] == 1) out++;
        for (int i = 1; i < light.length; i++) {
            light[i] = Math.max(light[i - 1], light[i]);
            if (light[i] - 1 == i) out++;
        }
        return out;
    }

    public int numTimesAllBlue(int[] light) {
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.add(light[0]);
        for (int i = 1; i < light.length; i++) {
            if (pq.peek() == i)
                count++;
            pq.offer(light[i]);
        }
        if (pq.peek() == light.length)
            count++;
        return count;
    }
}
