package leetcode.veryCommon.array;

import java.util.*;
import java.util.stream.IntStream;

public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        Arrays.stream(arr1).boxed().forEach(a -> mp.put(a, mp.getOrDefault(a, 0) + 1));
        Deque<Integer> outQ = new LinkedList<>();
        Arrays.stream(arr2).forEach(el -> {
            IntStream.rangeClosed(1, mp.get(el)).forEach(a -> outQ.add(el));
            mp.remove(el);
        });

        mp.entrySet().stream().sorted((a, b) -> a.getKey() - b.getKey()).forEach(es -> {
            IntStream.rangeClosed(1, es.getValue()).forEach(a -> outQ.add(es.getKey()));
        });
        return outQ.stream().mapToInt(a -> (int) a).toArray();
    }
}
