package leetcode.veryCommon.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//Thiking for a little while, you will easily find out this theorem on a connected graph:
//
//An edge is a critical connection, if and only if it is not in a cycle.
//So, if we know how to find cycles, and discard all edges in the cycles, then the remaining connections are a complete collection of critical connections.

public class CriticalConnectionsinaNetwork {
    int depth = 0;
    List<List<Integer>> output = new LinkedList<>();
    HashMap<Integer, Integer> firstFoundDepth = new HashMap<>();
    HashMap<Integer, List<Integer>> graph = new HashMap<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new HashMap<>();
        connections.stream().forEach(xy -> {
            graph.computeIfAbsent(xy.get(0), x -> new LinkedList<>()).add(xy.get(1));
            graph.computeIfAbsent(xy.get(1), x -> new LinkedList<>()).add(xy.get(0));
        });
        helper(0, -1);
        return output;
    }

    public int helper(int root, int parent) {
        firstFoundDepth.put(root, depth);
        int currDepth = depth, returnMin = depth;
        depth++;
        for (int next : graph.get(root)) {
            if (next == parent)
                continue;
            int nextDepth = firstFoundDepth.getOrDefault(next, -1);
            if (nextDepth == -1) {
                nextDepth = helper(next, root);
                if (currDepth < nextDepth)
                    output.add(Arrays.asList(new Integer[]{root, next}));
            }
            returnMin = Math.min(nextDepth, returnMin);
        }
        firstFoundDepth.put(root, returnMin);
        return returnMin;
    }
}
