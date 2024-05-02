package leetcode.veryCommon.topologicalSort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelCourses {
    public int minimumSemesters(int n, int[][] relations) {
        int output = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        IntStream.range(1, n + 1).forEach(i -> {
            graph.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        });

        for (int[] edge : relations) {
            graph.get(edge[0]).add(edge[1]);
            inDegree.put(edge[1], inDegree.get(edge[1]) + 1);
        }

        Queue<Integer> q = new LinkedList<>();
        q.addAll(
                inDegree
                        .entrySet()
                        .stream()
                        .filter(es -> es.getValue() == 0)
                        .map(es -> es.getKey())
                        .collect(Collectors.toList())
        );
        q.add(null);
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            if (curr == null) {
                output++;
                if (!q.isEmpty()) q.add(null);
            } else {
                for (Integer child : graph.get(curr)) {
                    int in = inDegree.get(child);
                    inDegree.put(child, --in);
                    if (in == 0)
                        q.add(child);
                }
            }
        }
        output = inDegree.values().stream().filter(i -> i != 0).count() > 0 ? -1 : output;
        return output;
    }
}
