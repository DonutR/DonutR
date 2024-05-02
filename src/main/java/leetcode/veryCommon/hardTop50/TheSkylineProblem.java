package leetcode.veryCommon.hardTop50;

import scala.Int;

import java.util.*;
import java.util.stream.Collectors;

public class TheSkylineProblem {
    public class CriticalPoint {
        int x;
        int h;
        char t;

        public CriticalPoint(int x, int h, char t) {
            this.x = x;
            this.h = h;
            this.t = t;
        }

        @Override
        public String toString() {
            return "CriticalPoint{" +
                    "x=" + x +
                    ", h=" + h +
                    ", t=" + t +
                    '}';
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> out = new LinkedList<>();
        HashMap<Integer, Integer> outMap = new HashMap<>();
        List<CriticalPoint> points = new LinkedList<>();
        HashMap<CriticalPoint, CriticalPoint> endStartPair = new HashMap<>();
        Arrays.stream(buildings).forEach(p -> {
            CriticalPoint s = new CriticalPoint(p[0], p[2], 's');
            CriticalPoint e = new CriticalPoint(p[1], p[2], 'e');
            points.add(s);
            points.add(e);
            endStartPair.put(e, s);
        });
        Collections.sort(points, (a, b) -> {
            if (a.x != b.x) return a.x - b.x;
            else {
                //[2,15,12],[15,20,10] to resolve edge case like this
                if ((a.t == 'e' && b.t == 's') || (a.t == 's' && b.t == 'e'))
                    //[[0,2,3],[2,5,3]] to resolve edge case like this
                    return (b.h - a.h == 0 ? (a.t == 's' ? -1 : 1) : b.h - a.h);
                    //[15,20,10],[16,20,30] to resolve edge case like this
                else if (a.t == 'e' && b.t == 'e')
                    return a.h - b.h;
                    //[2,9,10],[2,15,12]
                else
                    return b.h - a.h;
            }
        });
        System.out.println(points);
        PriorityQueue<CriticalPoint> q = new PriorityQueue<>((a, b) -> b.h - a.h);
        int MAX_VAL = 0;
        for (CriticalPoint c : points) {
            if (c.t == 'e')
                q.remove(endStartPair.get(c));
            else q.add(c);
            if (!q.isEmpty() && q.peek().h != MAX_VAL) {
                outMap.put(c.x, q.peek().h);
                MAX_VAL = q.peek().h;
            } else if (q.isEmpty()) {
                outMap.put(c.x, 0);
                MAX_VAL = 0;
            }
        }
        return outMap.entrySet()
                .stream()
                .sorted((a, b) -> a.getKey() - b.getKey())
                .map(es -> Arrays.asList(new Integer[]{es.getKey(), es.getValue()}))
                .collect(Collectors.toList());
    }
}
