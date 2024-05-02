package leetcode.veryCommon.graph;

import java.util.*;
import java.util.stream.Collectors;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> emailNameMap = new HashMap<>();
        HashMap<String, LinkedList<String>> graphAdjList = new HashMap<>();

        for (List<String> act : accounts) {
            String name = act.get(0);
            String firstEmail = act.get(1);
            emailNameMap.put(firstEmail, name);
            for (String email : act.subList(1, act.size())) {
                graphAdjList.computeIfAbsent(email, x -> new LinkedList<>()).add(firstEmail);
                graphAdjList.computeIfAbsent(firstEmail, x -> new LinkedList<>()).add(email);
                emailNameMap.put(email, name);
            }
        }

        Set<String> visitedEmail = new HashSet<>();
        List<List<String>> output = new LinkedList<>();
        for (Map.Entry<String, String> es : emailNameMap.entrySet()) {
            if (!visitedEmail.contains(es.getKey())) {
                List<String> out = new LinkedList<>();
                dfs(graphAdjList, visitedEmail, es.getKey(), out);
                dfsStack(graphAdjList, visitedEmail, es.getKey(), out);
                bfsQueue(graphAdjList, visitedEmail, es.getKey(), out);
                List<String> user = new LinkedList<>();
                user.add(es.getValue());
                user.addAll(out.stream().sorted(String::compareTo).collect(Collectors.toList()));
                output.add(user);
            }
        }
        return output;
    }

    public void dfs(HashMap<String, LinkedList<String>> graphAdjList, Set<String> visited, String node, List<String> out) {
        if (!visited.contains(node)) {
            visited.add(node);
            out.add(node);
            for (String nodeChild : graphAdjList.get(node)) {
                dfs(graphAdjList, visited, nodeChild, out);
            }
        }
    }

    public void dfsStack(HashMap<String, LinkedList<String>> graph, Set<String> visited, String node, List<String> output) {
        Stack<String> stk = new Stack<>();
        stk.add(node);
        visited.add(node);
        while (!stk.isEmpty()) {
            String curr = stk.pop();
            output.add(curr);
            for (String next : graph.get(curr)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    stk.add(next);
                }
            }
        }
    }

    public void bfsQueue(HashMap<String, LinkedList<String>> graph, Set<String> visited, String node, List<String> output) {
        Queue<String> q = new LinkedList<>();
        q.add(node);
        visited.add(node);
        while (!q.isEmpty()) {
            String curr = q.poll();
            output.add(curr);
            for (String next : graph.get(curr)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    q.add(next);
                }
            }
        }
    }
}
