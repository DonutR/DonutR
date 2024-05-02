package leetcode.veryCommon.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SumofDistancesinTree {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        Arrays.stream(edges).forEach(x -> {
            graph.computeIfAbsent(x[0], y -> new ArrayList<>()).add(x[1]);
            graph.computeIfAbsent(x[1], y -> new ArrayList<>()).add(x[0]);
        });
        HashMap<Integer, int[]> nodeStats = new HashMap<>();
        dfs(graph, 0, -1, 0, nodeStats);
        nodeStats.entrySet().forEach(es -> System.out.println(es.getKey() + " " + es.getValue()[0] + "--" + es.getValue()[1]));
        return null;
    }

    public int[] dfs(HashMap<Integer, List<Integer>> graph, int current, int parent, int depthFromStart, HashMap<Integer, int[]> nodeStats) {
        int childPathSum = depthFromStart, childCt = 1;
        for (int next : graph.get(current)) {
            if (next != parent) {
                int[] result = dfs(graph, next, current, depthFromStart + 1, nodeStats);
                childPathSum += result[0];
                childCt += result[1];
            }
        }
        nodeStats.put(current, new int[]{childPathSum - (graph.get(current).size() - 1) * depthFromStart, childCt});
        return new int[]{childPathSum, childCt};
    }
}
