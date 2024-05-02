package leetcode.veryCommon.hardTop50;

import java.util.Stack;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[] histogram = new int[n];
        int maxRect = 0;
        for (int j = 0; j < n; j++)
            histogram[j] = matrix[0][j]-'0';
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++)
                histogram[j] = matrix[i][j]-'0' == 0 ? 0 : histogram[j] + matrix[i][j]-'0';
            maxRect = Math.max(maxRect, largestRectangleArea(histogram));
        }
        return maxRect;
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
