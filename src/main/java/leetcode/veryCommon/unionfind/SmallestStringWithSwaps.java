package leetcode.veryCommon.unionfind;

import java.util.*;
import java.util.stream.Collectors;

public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int N = s.length();
        char[] charArray = s.toCharArray();
        DSU dsu = new DSU(N);
        for (List<Integer> pair : pairs)
            dsu.union(pair.get(0), pair.get(1));
        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < N; i++)
            groups.computeIfAbsent(dsu.find(i), x -> new LinkedList<>()).add(i);

        System.out.println(groups);
        groups
                .entrySet().stream()
                .filter(es -> es.getValue().size() > 1)
                .forEach(es -> {
                    List<Character> sb = new ArrayList<>();
                    for (int i : es.getValue())
                        sb.add(charArray[i]);
                    sb.sort((a, b) -> a - b);
                    int i = 0;
                    es.getValue().sort((a, b) -> a - b);
                    for (int j : es.getValue())
                        charArray[j] = sb.get(i++);
                });

        return new String(charArray);
    }

    class DSU {
        int[] parent;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        public int find(int n) {
            if (parent[n] != n)
                parent[n] = find(parent[n]);
            return parent[n];
        }

        public void union(int n1, int n2) {
            parent[find(n1)] = find(n2);
        }
    }

}
