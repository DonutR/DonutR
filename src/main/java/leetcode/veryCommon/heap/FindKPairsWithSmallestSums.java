package leetcode.veryCommon.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * <p>
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 * <p>
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [1,1],[1,1]
 * Explanation: The first 2 pairs are returned from the sequence:
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [1,3],[2,3]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<Integer[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        if (nums2.length == 0) return new LinkedList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!queue.isEmpty() && (nums1[i] + nums2[0]) > queue.peek()[2]&&queue.size()==k)
                break;
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                queue.add(new Integer[]{nums1[i], nums2[j], sum});
                if (queue.size() > k)
                    queue.poll();
            }
        }
        return queue.stream()
                .map(a -> Arrays.asList(new Integer[]{a[0], a[1]}))
                .collect(Collectors.toList());
    }
}
