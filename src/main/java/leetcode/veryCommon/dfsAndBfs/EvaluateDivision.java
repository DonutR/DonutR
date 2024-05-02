package leetcode.veryCommon.dfsAndBfs;

import java.util.*;

public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            graph.computeIfAbsent(equations.get(i).get(0), x -> new HashMap<String, Double>())
                    .put(equations.get(i).get(1), values[i]);
            graph.computeIfAbsent(equations.get(i).get(1), x -> new HashMap<String, Double>())
                    .put(equations.get(i).get(0), 1 / values[i]);
        }
        double[] out = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String n = queries.get(i).get(0), d = queries.get(i).get(1);
            if (!graph.containsKey(n) || !graph.containsKey(d))
                out[i] = -1.0;
            else if (n.equals(d))
                out[i] = 1;
            else
                out[i] = backTracking(graph, new HashSet<String>(), queries.get(i).get(0), queries.get(i).get(1), 1);
        }

        return out;
    }

    public double backTracking(HashMap<String, HashMap<String, Double>> graph, HashSet<String> visited, String curr, String dest, double accProd) {
        if (!visited.contains(curr)) {
            visited.add(curr);
            if (graph.get(curr).containsKey(dest))
                return accProd * graph.get(curr).get(dest);
            else {
                for (Map.Entry<String, Double> desSet : graph.get(curr).entrySet()) {
                    double ret = backTracking(graph, visited, desSet.getKey(), dest, accProd * desSet.getValue());
                    if (ret != -1.0)
                        return ret;
                }
            }
            visited.remove(curr);
        }
        return -1.0;
    }
}
