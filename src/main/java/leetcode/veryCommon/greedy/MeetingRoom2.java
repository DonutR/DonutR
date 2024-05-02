package leetcode.veryCommon.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

//Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
//Example : [2,15],[36,45],[9,29],[16,23],[4,9]
public class MeetingRoom2 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, ((a, b) -> a[0] - b[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int out = 0;
        for (int[] itv : intervals) {
            if (heap.isEmpty()) {
                heap.add(itv[1]);
                out++;
            } else {
                if (itv[0] >= heap.peek())
                    heap.poll();
                else
                    out++;
                heap.add(itv[1]);
            }
        }
        return out;
    }
}
