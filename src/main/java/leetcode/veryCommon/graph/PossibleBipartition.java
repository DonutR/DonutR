package leetcode.veryCommon.graph;

import scala.Int;

import java.util.*;
import java.util.stream.IntStream;

public class PossibleBipartition {
    //DFS Graph Colouring
    public boolean possibleBipartition1(int N, int[][] dislikes) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        Arrays.stream(dislikes).forEach(dl -> {
            graph.computeIfAbsent(dl[0], x -> new LinkedList<>()).add(dl[1]);
            graph.computeIfAbsent(dl[1], x -> new LinkedList<>()).add(dl[0]);
        });
        HashMap<Integer, Boolean> nodeColourMap = new HashMap<>();
        for (int node = 1; node <= N; node++) {
            if (!nodeColourMap.containsKey(node)) {
                if (!dfs(node, true, graph, nodeColourMap))
                    return false;
            }
        }
        return true;
    }

    public boolean dfs(int node, boolean colour, HashMap<Integer, List<Integer>> graph, HashMap<Integer, Boolean> nodeColourMap) {
        if (nodeColourMap.containsKey(node))
            return nodeColourMap.get(node) == colour;
        else {
            nodeColourMap.put(node, colour);
            for (int next : graph.getOrDefault(node, new LinkedList<>())) {
                if (!dfs(next, !colour, graph, nodeColourMap)) {
                    System.out.println(next);
                    return false;
                }
            }
            return true;
        }
    }

    //BFS Graph Colouring
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        IntStream.range(1, N + 1).forEach(n -> graph.put(n, new ArrayList<>()));
        Arrays.stream(dislikes).forEach(n -> {
            graph.get(n[0]).add(n[1]);
            graph.get(n[1]).add(n[0]);
        });

        Map<Integer, Integer> graphColour = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < N; i++) {
            if (!graphColour.containsKey(i)) {
                q.add(i);
                graphColour.put(i, 0);
                while (!q.isEmpty()) {
                    int node = q.poll();
                    int thisColour = graphColour.get(node);
                    for (int next : graph.get(node)) {
                        if (!graphColour.containsKey(next)) {
                            graphColour.put(next, thisColour == 0 ? 1 : 0);
                            q.add(next);
                        } else if (graphColour.containsKey(next) && graphColour.get(next) == thisColour)
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
