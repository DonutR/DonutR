package leetcode.veryCommon.heap;

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> mp = new HashMap<>();
        Arrays.stream(words).forEach(a -> mp.put(a, mp.getOrDefault(a, 0) + 1));

        Queue<Map.Entry<String, Integer>> q = new PriorityQueue<>(
                (a, b) ->
                        a.getValue() - b.getValue()==0?
                                b.getKey().compareTo(a.getKey()):
                                a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> es : mp.entrySet()) {
            q.add(es);
            if (q.size() > k)
                q.poll();
        }

        return q.stream()
                .sorted((a,b)->b.getValue()-a.getValue()==0?a.getKey().compareTo(b.getKey()):b.getValue()-a.getValue())
                .map(a -> a.getKey())
                .collect(Collectors.toList());
    }
}
