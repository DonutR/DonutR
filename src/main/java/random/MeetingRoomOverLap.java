package random;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MeetingRoomOverLap {
    //    My Calendar II
    class MyCalendarTwo {
        List<Integer[]> overLap;
        List<Integer[]> calendar;

        public MyCalendarTwo() {
            overLap = new ArrayList<>();
            calendar = new ArrayList<>();
        }

        public boolean book(int thisStart, int thisEnd) {
            //Positive check of overlap
            for (Integer[] meeting : overLap)
                if (meeting[0] < thisEnd && thisStart < meeting[1])
                    return false;

            for (Integer[] meeting : calendar)
                if (meeting[0] < thisEnd && thisStart < meeting[1])
                    overLap.add(new Integer[]{Math.max(meeting[0], thisStart), Math.min(meeting[1], thisEnd)});

            calendar.add(new Integer[]{thisStart, thisEnd});
            return true;
        }
    }

    //    My Calendar I
    class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        MyCalendar() {
            calendar = new TreeMap();
        }

        public boolean book(int thisStart, int thisEnd) {
            Integer prevStart = calendar.floorKey(thisStart),
                    nextStart = calendar.ceilingKey(thisStart),
                    prevEnd = null;
            //Negative check of overlap
            if (prevStart != null)
                prevEnd = calendar.get(prevStart);

            if ((prevStart == null || prevEnd <= thisStart) &&
                    (nextStart == null || thisEnd <= nextStart)) {
                calendar.put(thisStart, thisEnd);
                return true;
            }
            return false;
        }
    }

    class MyCalendarThree {
        TreeMap<Integer, Integer> timeLine;

        public MyCalendarThree() {
            timeLine = new TreeMap<>();
        }

        public int book(int start, int end) {
            timeLine.put(start, timeLine.getOrDefault(start, 0) + 1);
            timeLine.put(end, timeLine.getOrDefault(end, 0) - 1);
            int ans = 0, ongoing = 0;
            for (int count : timeLine.values()) {
                ans = Math.max(ans, ongoing + count);
            }
            return ans;
        }
    }
}
