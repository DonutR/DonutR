package leetcode.veryCommon.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * <p>
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * <p>
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        Queue<int[]> kQueue = new PriorityQueue<>((a, b) -> {
            double bD = Math.sqrt(Math.pow(b[0], 2) + Math.pow(b[1], 2));
            double aD = Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
            if (bD > aD)
                return 1;
            else if (bD < aD)
                return -1;
            else return 0;
        });
        for (int i = 0; i < points.length; i++) {
            kQueue.add(points[i]);
            if (kQueue.size() > K)
                kQueue.remove();
        }
        int[][] out = new int[kQueue.size()][2];
        return kQueue.toArray(out);
    }
}
