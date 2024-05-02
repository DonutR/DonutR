package random;

import java.util.Arrays;

public class MeetingRoomGreedy {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Long.compare((long)a[1],(long)b[1]));

        long currEnd = points[0][1], out = 1;

        for (int i = 1; i < points.length; i++) {
            if (currEnd >= points[i][0])
                continue;
            out++;
            currEnd = points[i][1];
        }
        return (int)out;
    }
}
