package leetcode.veryCommon.topologicalSort;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        HashSet<Integer>[] adj = new HashSet[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new HashSet<>();

        for (int[] edj : edges) {
            adj[edj[0]].add(edj[1]);
            adj[edj[1]].add(edj[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        int idx = 0;
        for (HashSet<Integer> hs : adj) {
            if (hs.size() == 1)
                leaves.add(idx);
            idx++;
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int l : leaves) {
                int lNext = adj[l].iterator().next();
                adj[lNext].remove(l);
                if (adj[lNext].size() == 1)
                    newLeaves.add(lNext);
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new HashSet<Integer>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new HashSet<Integer>()).add(e[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        while (n > 2) {
            leaves = graph.entrySet()
                    .stream()
                    .filter(es -> es.getValue().size() <= 1).map(es -> es.getKey())
                    .collect(Collectors.toList());
            n -= leaves.size();
            System.out.println(leaves);
            for (int l : leaves) {
                int lNext = graph.get(l).iterator().next();
                graph.get(lNext).remove(l);
                graph.remove(l);
            }
        }
        leaves = graph.entrySet()
                .stream()
                .filter(es -> es.getValue().size() <= 1).map(es -> es.getKey())
                .collect(Collectors.toList());
        return leaves;
    }
}
