package leetcode.veryCommon.array;

import java.util.TreeMap;

public class MyCalendarI {
    class MyCalendar {
        TreeMap<Integer, Integer> calender;

        public MyCalendar() {
            calender = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prevStart = calender.floorKey(start), nextStart = calender.ceilingKey(start);
            if ((prevStart == null || calender.get(prevStart) <= start) && (nextStart == null || nextStart >= end)) {
                calender.put(start, end);
                return true;
            }
            return false;
        }
    }
}
