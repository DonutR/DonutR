package leetcode.veryCommon.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReconstructItinerary {
    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        helper("JFK");
        return route;
    }

    public void helper(String airport) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty())
            helper(targets.get(airport).poll());
        route.add(0, airport);
    }
}
