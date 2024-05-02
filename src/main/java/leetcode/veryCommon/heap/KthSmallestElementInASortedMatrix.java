package leetcode.veryCommon.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * <p>
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * <p>
 * <p>
 * matrix = [
 * [ 1,  5,  9, 10],
 * [10, 11, 13, 14],
 * [12, 13, 15, 16],
 * [13, 14, 16, 17]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class KthSmallestElementInASortedMatrix {
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        Queue<Integer> pQ = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pQ.add(matrix[i][j]);
                if (pQ.size() > k)
                    pQ.poll();
            }
        }
        return pQ.poll();
    }

    //    Binary Search Approach
//    We are done here, but let's think about this problem in another way:
//    The key point for any binary search is to figure out the "Search Space". For me, I think there are two kind of "Search Space" -- index and range(the range from the smallest number to the biggest number). Most usually, when the array is sorted in one direction, we can use index as "search space", when the array is unsorted and we are going to find a specific number, we can use "range".
//
//    Let me give you two examples of these two "search space"
//
//    index -- A bunch of examples -- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ ( the array is sorted)
//    range -- https://leetcode.com/problems/find-the-duplicate-number/ (Unsorted Array)
//    The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions, we can not find a linear way to map the number and its index.
//
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int lo = matrix[0][0], hi = matrix[n][n];
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int count = 0;
            for (int i = 0; i <= n; i++) {
                int j = n;
                while (j >= 0 && matrix[i][j] > mid) j--;
                count += j + 1;
            }
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
