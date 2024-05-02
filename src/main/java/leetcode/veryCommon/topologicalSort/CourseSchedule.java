package leetcode.veryCommon.topologicalSort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graphAdjList = new HashMap<>();
        HashMap<Integer, Integer> nodeInDegree = new HashMap<>();

        IntStream stream = IntStream.range(0, numCourses);
        stream.forEach(a -> {
            graphAdjList.put(a, new LinkedList<>());
            nodeInDegree.put(a, 0);
        });

        Arrays.stream(prerequisites).forEach(a -> {
            graphAdjList.get(a[1]).add(a[0]);
            nodeInDegree.put(a[0], nodeInDegree.getOrDefault(a[0], 0) + 1);
        });

        List<Integer> sortedList = new ArrayList<>();
        Queue<Integer> bfsQ = new LinkedList<Integer>(
                nodeInDegree
                        .entrySet().stream()
                        .filter(a -> a.getValue() == 0)
                        .map(a -> a.getKey())
                        .collect(Collectors.toList()));

        while (!bfsQ.isEmpty()) {
            int curNode = bfsQ.poll();
            sortedList.add(curNode);
            for (int child : graphAdjList.get(curNode)) {
                int inDegreeCt = nodeInDegree.get(child);
                if (--inDegreeCt == 0) bfsQ.add(child);
                nodeInDegree.put(child, inDegreeCt);
            }
        }

        System.out.println(sortedList);
        return sortedList.size() == numCourses;
    }

}
