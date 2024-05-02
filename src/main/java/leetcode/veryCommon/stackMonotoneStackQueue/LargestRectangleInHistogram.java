package leetcode.veryCommon.stackMonotoneStackQueue;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * <p>
 * <p>
 * <p>
 * <p>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 100, 3};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea2(int[] heights) {
        int area = 0, N = heights.length;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i <= N; i++) {
            //see the condition in above loop and next loop
            //i=N os handled in same loop
            while (!s.isEmpty() && heights[s.peek()] > (i < N ? heights[i] : Integer.MIN_VALUE)) {
                int h = heights[s.pop()];
                int j = s.isEmpty() ? -1 : s.peek();
                int w = i - j - 1;
                area = Math.max(area, w * h);

            }
            s.push(i);
        }
        return area;
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0, curIdx = 0;
        for (; curIdx < heights.length; curIdx++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[curIdx]) {
                int tpIdx = stack.pop();
                int curHeight = heights[tpIdx];
                int width = stack.isEmpty() ? curIdx : curIdx - stack.peek() - 1;
                maxArea = Math.max(maxArea, width * curHeight);
            }
            stack.push(curIdx);
        }
        while (!stack.isEmpty()) {
            int tpIdx = stack.pop();
            int tpHeight = heights[tpIdx];
            int width = stack.isEmpty() ? curIdx : curIdx - stack.peek() - 1;
            maxArea = Math.max(maxArea, width * tpHeight);
        }
        return maxArea;
    }
}
