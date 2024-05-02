package leetcode.veryCommon.topologicalSort;

import java.util.*;
import java.util.stream.Collectors;

public class FindAllRecipes {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> suppliesSet = Arrays.stream(supplies).collect(Collectors.toSet());

        HashMap<String, List<String>> graphAdjList = new HashMap<>();
        HashMap<String, Integer> nodeInDegree = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            for (String ing : ingredients.get(i)) {
                graphAdjList.computeIfAbsent(ing, x -> new ArrayList<>()).add(recipes[i]);
                graphAdjList.computeIfAbsent(recipes[i], x -> new ArrayList<>());
                nodeInDegree.put(recipes[i], nodeInDegree.getOrDefault(recipes[i], 0) + 1);
                nodeInDegree.putIfAbsent(ing, 0);
            }
        }

        List<String> sortedOut = new ArrayList<>();
        Queue<String> bfsQ = new LinkedList<String>(
                nodeInDegree
                        .entrySet().stream()
                        .filter(a -> a.getValue() == 0 && suppliesSet.contains(a.getKey()))
                        .map(a -> a.getKey())
                        .collect(Collectors.toList()));
        while (!bfsQ.isEmpty()) {
            String node = bfsQ.poll();
            sortedOut.add(node);
            for (String childNode : graphAdjList.get(node)) {
                nodeInDegree.put(childNode, nodeInDegree.getOrDefault(childNode, 0) - 1);
                if (nodeInDegree.get(childNode) == 0) bfsQ.add(childNode);
            }
        }
        sortedOut.removeAll(suppliesSet);
        return sortedOut;
    }
}
