package leetcode.veryCommon.hardTop50;

import java.util.*;
import java.util.stream.Collectors;

public class BusRoutes {
    public int numBusesToDestinationStopAsNode(int[][] routes, int S, int T) {
        HashMap<Integer, List<Integer[]>> adjList = new HashMap<>();
        int N = routes.length;
        for (int j = 0; j < routes.length; j++) {
            int[] route = routes[j];
            for (int i = 0; i < route.length; i++)
                adjList.computeIfAbsent(route[i], x -> new LinkedList<>()).add(i + 1 <= N ? new Integer[]{route[i + 1], j} : new Integer[]{route[0], j});
        }

        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{S, 0});
        q.add(null);
        int out = 0;
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> usedBus = new HashSet<>();

        while (!q.isEmpty()) {
            Integer[] curStop = q.poll();
            if (curStop == null) {
                out++;
                if (!q.isEmpty())
                    q.add(null);
            } else {
                usedBus.add(curStop[1]);
                if (curStop[0] == T)
                    return usedBus.size();
                visited.add(curStop[0]);
                for (Integer[] s : adjList.get(curStop[0]))
                    if (!visited.contains(s)) q.add(s);
            }
        }
        return -1;
    }
}
