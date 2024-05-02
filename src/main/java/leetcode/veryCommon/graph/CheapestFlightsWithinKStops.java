package leetcode.veryCommon.graph;

import scala.Int;

import java.util.*;

public class CheapestFlightsWithinKStops {

    public int findCheapestPriceV2(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> weightedGraph = new HashMap<>();
        Arrays.stream(flights)
                .forEach(f -> weightedGraph.computeIfAbsent(f[0], x -> new HashMap<>()).put(f[1], f[2]));
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, src, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops <= k) {
                Map<Integer, Integer> adj = weightedGraph.getOrDefault(city, new HashMap<>());
                for (Map.Entry<Integer, Integer> a : adj.entrySet()) {
                    pq.add(new int[]{price + a.getValue(), a.getKey(), stops + 1});
                }
            }
        }
        return -1;
    }
}
