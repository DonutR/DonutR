package leetcode.veryCommon.prefixSum;

import java.util.Arrays;

public class MaximumSumof3NonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int m) {
        int N = nums.length;
        int[] prefixSm = new int[N], W = new int[N - m + 1], left = new int[W.length], right = new int[W.length];
        prefixSm[0] = nums[0];
        for (int i = 1; i < N; i++)
            prefixSm[i] = nums[i] + prefixSm[i - 1];
        W[0] = prefixSm[m - 1];
        for (int i = 1; i < W.length; i++)
            W[i] = prefixSm[i + m - 1] - prefixSm[i - 1];

        //Index of max value in left side of an index
        int bestValIdx = 0;
        for (int i = 0; i < W.length; i++) {
            if (W[i] > W[bestValIdx]) bestValIdx = i;
            left[i] = bestValIdx;
        }
        //Index of max value in right side of an index
        bestValIdx = W.length - 1;
        for (int i = W.length - 1; i >= 0; i--) {
            if (W[i] >= W[bestValIdx]) bestValIdx = i;
            right[i] = bestValIdx;
        }
        int[] ans = {-1, -1, -1};
        for (int j = m; j < W.length - m; j++) {
            int i = left[j - m], k = right[j + m];

            if (ans[0] == -1 || W[i] + W[j] + W[k]
                    > W[ans[0]] + W[ans[1]] + W[ans[2]])
                ans = new int[]{i, j, k};
        }
        return ans;
    }
}
