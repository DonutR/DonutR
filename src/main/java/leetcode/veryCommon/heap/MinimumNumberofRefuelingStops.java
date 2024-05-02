package leetcode.veryCommon.heap;

import java.util.PriorityQueue;

public class MinimumNumberofRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));
        int ans = 0, currMax = startFuel;
        for (int[] station : stations) {
            if(currMax<station[0])
                return -1;
            if (currMax >= target)
                return ans;
            else {
                if (!pq.isEmpty()) {
                    ans++;
                    currMax += pq.poll()[1];
                }
                pq.add(station);
            }
        }
        while (!pq.isEmpty()) {
            currMax += pq.poll()[1];
            if (currMax >= target)
                return ans++;
        }
        return currMax >= target ? ans : -1;
    }
}
