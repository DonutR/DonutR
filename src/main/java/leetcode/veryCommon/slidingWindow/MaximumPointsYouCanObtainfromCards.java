package leetcode.veryCommon.slidingWindow;

public class MaximumPointsYouCanObtainfromCards {
    public int maxScore(int[] cardPoints, int k) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        max = sum;
        for (int i = k - 1, j = cardPoints.length - 1; i >= 0; i--, j--) {
            sum = sum - cardPoints[i] + cardPoints[j];
            if (sum > max)
                max = sum;
        }
        return max;
    }
}
