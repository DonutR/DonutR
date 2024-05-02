package leetcode.veryCommon.greedy;

import java.util.Arrays;
import java.util.Stack;

public class MinimumNumberofArrowstoBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            else return a[0] - b[0];
        });
        Stack<int[]> stack = new Stack<>();
        stack.push(points[0]);
        for (int i = 1; i < points.length; i++) {
            int[] topPoint = stack.peek();
            if (points[i][0] >= topPoint[0] && points[i][0] <= topPoint[1]) {
                stack.pop();
                int[] tmp = {Math.max(topPoint[0], points[i][0]), Math.min(topPoint[1], points[i][1])};
                stack.push(tmp);
            } else stack.push(points[i]);
        }
        return stack.size();
    }
}
