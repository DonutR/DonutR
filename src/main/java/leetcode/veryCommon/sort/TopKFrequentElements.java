package leetcode.veryCommon.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> ctMap = new HashMap<>();
        Arrays.stream(nums).forEach(a -> ctMap.put(a, ctMap.getOrDefault(a, 0) + 1));
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        ctMap.entrySet().stream().forEach(es -> {
            q.add(es);
            if (q.size() > k)
                q.poll();
        });
        return q.stream().sorted((a, b) -> b.getValue() - a.getValue()).map(a -> a.getKey()).mapToInt(a -> a).toArray();
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> ctMap = new HashMap<>();
        Arrays.stream(nums).forEach(a -> ctMap.put(a, ctMap.getOrDefault(a, 0) + 1));
        System.out.println();
        int[][] inpArr = new int[ctMap.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> es : ctMap.entrySet()) {
            inpArr[i] = new int[]{es.getValue(), es.getKey()};
            i++;
        }
        Arrays.stream(inpArr).forEach(a -> System.out.println(a[0] + a[1]));
        int pivote = quickSelect(inpArr, 0, inpArr.length - 1, k);
        System.out.println(pivote);
        return Arrays.stream(Arrays.copyOfRange(inpArr, pivote, inpArr.length)).mapToInt(a -> a[1]).toArray();
    }

    public static int quickSelect(int[][] inpArr, int start, int end, int k) {
        int pivotPos = quickSortPartition(inpArr, start, end);
        if (inpArr.length - pivotPos == k)
            return pivotPos;
        if (start < pivotPos - 1 && k > inpArr.length - pivotPos)
            return quickSelect(inpArr, start, pivotPos - 1, k);
        else if (pivotPos + 1 < end && k < inpArr.length - pivotPos)
            return quickSelect(inpArr, pivotPos + 1, end, k);
        return pivotPos;
    }

    public static int quickSortPartition(int[][] inpArr, int start, int end) {
        int i = start, pivot = inpArr[end][0];
        int[] swap;
        for (int j = start; j <= end; j++) {
            if (inpArr[j][0] <= pivot) {
                swap = inpArr[i];
                inpArr[i] = inpArr[j];
                inpArr[j] = swap;
                i++;
            }
        }
        return i - 1;
    }
}
