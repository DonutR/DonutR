package leetcode.veryCommon.graph;

import scala.Int;

import java.util.*;

public class NetworkDelayTime {
    //Dijkstra's modified just another approach
    public static int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> distanceMap = new HashMap<>();
        Arrays.stream(times).forEach(x ->
                graph.computeIfAbsent(x[0], y -> new HashMap<>()).put(x[1], x[2]));
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{K, 0});
        distanceMap.put(K, 0);
        while (!q.isEmpty()) {
            int[] currNodeArr = q.poll();
            int currNode = currNodeArr[0], currDist = currNodeArr[1];
            graph.getOrDefault(currNode, new HashMap<>()).entrySet().stream().forEach(es -> {
                int nextNode = es.getKey(), nextNodeDist = es.getValue() + currDist;
                if (!distanceMap.containsKey(nextNode) || distanceMap.get(nextNode) > nextNodeDist) {
                    distanceMap.put(nextNode, nextNodeDist);
                    q.add(new int[]{nextNode, nextNodeDist});
                }
            });
        }
        if (distanceMap.size() != N) return -1;
        return distanceMap.values().stream().max((a, b) -> a - b).orElseGet(() -> 0);
    }
}
