package leetcode.veryCommon.topologicalSort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CourseScheduleII {

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> bfsQueue = new LinkedList<>();
        List<Integer> out = new ArrayList<>();

        Arrays.stream(prerequisites).forEach(a -> {
            List<Integer> nextCourses = adjList.getOrDefault(a[1], new ArrayList<Integer>());
            nextCourses.add(a[0]);
            adjList.put(a[1], nextCourses);
            Integer inCt = inDegree.getOrDefault(a[0], 0);
            inDegree.put(a[0], ++inCt);
        });

        IntStream.range(0, numCourses).forEach(a -> {
            if (!inDegree.containsKey(a)) bfsQueue.add(a);
        });

        while (!bfsQueue.isEmpty()) {
            int curCourse = bfsQueue.poll();
            out.add(curCourse);
            for (int nextCourse : adjList.getOrDefault(curCourse, new ArrayList<Integer>())) {
                int inCt = inDegree.getOrDefault(nextCourse, 0);
                if (--inCt == 0) bfsQueue.add(nextCourse);
                inDegree.put(nextCourse, inCt);
            }
        }

        return out.size() < numCourses ? new int[0] : out.stream().mapToInt(a -> a).toArray();
    }
}