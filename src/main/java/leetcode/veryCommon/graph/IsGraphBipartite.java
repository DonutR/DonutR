package leetcode.veryCommon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

/*
Approach #1: Coloring by Depth-First Search [Accepted]
Intuition

Color a node blue if it is part of the first set, else red. We should be able to greedily color the graph if and only if it is bipartite: one node being blue implies all it's neighbors are red, all those neighbors are blue, and so on.


Diagram of coloring neighbors of nodes

Algorithm

We'll keep an array (or hashmap) to lookup the color of each node: color[node]. The colors could be 0, 1, or uncolored (-1 or null).

We should be careful to consider disconnected components of the graph, by searching each node. For each uncolored node, we'll start the coloring process by doing a depth-first-search on that node. Every neighbor gets colored the opposite color from the current node. If we find a neighbor colored the same color as the current node, then our coloring was impossible.

To perform the depth-first search, we use a stack. For each uncolored neighbor in graph[node], we'll color it and add it to our stack, which acts as a sort of "todo list" of nodes to visit next. Our larger loop for start... ensures that we color every node.
 */

public class IsGraphBipartite {
    public boolean isBipartiteBFS(int[][] graph) {
        //This array can have 3 values 0-> Not visited, 1-> Red, 2-> Blue
        int[] visited = new int[graph.length];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0) {
                q.add(i);
                visited[i] = 1;
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int n : graph[cur]) {
                        if (visited[n] != 0 && visited[n] == visited[cur])
                            return false;
                        else if (visited[n] == 0) {
                            q.add(n);
                            visited[n] = visited[cur] == 1 ? 2 : 1;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartiteDFS(int[][] graph) {
        //This array can have 3 values 0-> Not visited, 1-> Red, 2-> Blue
        int[] visited = new int[graph.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0) {
                stack.add(i);
                visited[i] = 1;
                while (!stack.isEmpty()) {
                    int cur = stack.pop();
                    for (int n : graph[cur]) {
                        if (visited[n] != 0 && visited[n] == visited[cur])
                            return false;
                        else if (visited[n] == 0) {
                            stack.add(n);
                            visited[n] = visited[cur] == 1 ? 2 : 1;
                        }
                    }
                }
            }
        }
        return true;
    }

    int[] visited;
    int[][] graph;

    public boolean isBipartiteRec(int[][] graph) {
        visited = new int[graph.length];
        this.graph = graph;
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (!helper(i)) return false;
            }
        }
        return true;
    }

    public boolean helper(int root) {
        for (int n : graph[root]) {
            if (visited[n] == 0) {
                visited[n] = visited[root] == 1 ? 2 : 1;
                if (!helper(n)) return false;
            } else if (visited[n] != 0 && visited[root] == visited[n])
                return false;
        }
        return true;
    }
}
