package random;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomLineSweepGreedy {
    //Merged Timeline --- Sweep Line Approach
    public int minMeetingRooms(int[][] intervals) {
        int[][] timeline = new int[intervals.length * 2][2];
        for (int i = 0, j = 0; i < intervals.length; i++, j += 2) {
            timeline[j] = new int[]{intervals[i][0], 0};
            timeline[j + 1] = new int[]{intervals[i][1], 1};
        }

        Arrays.sort(timeline, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });

        int out = 0, meetingCounter = 0;
        for (int[] t : timeline) {
            if (t[1] == 0)
                meetingCounter++;
            else meetingCounter--;
            out = Math.max(out, meetingCounter);
        }
        return out;
    }

    //Normal priority queue implementation
    public int minMeetingRoomsStack(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int out = 0;
        for (int[] interval : intervals) {
            while (!pq.isEmpty() && pq.peek()[1] <= interval[0])
                pq.poll();
            pq.add(interval);
            out = Math.max(out, pq.size());
        }
        return out;
    }
}
