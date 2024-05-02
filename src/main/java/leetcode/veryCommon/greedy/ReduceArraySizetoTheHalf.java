package leetcode.veryCommon.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReduceArraySizetoTheHalf {
    //Count the occurrences of each number using a hash map.
    //Sort occurrences in the descending order using a multiset.
    //Greedily sum occurrences from largest to smallest until it's equal or greater the half size of the array.
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0, half = arr.length / 2, sum = 0;
        Arrays.stream(arr).boxed().
                forEach(a -> map.put(a, map.getOrDefault(a, 0) + 1));

        List<Integer> tmpList = map.values().stream().sorted((a, b) -> b - a).collect(Collectors.toList());
        for (Integer i : tmpList) {
            sum += i;
            if (sum >= half)
                break;
            count++;
        }
        return count;
    }
}
