package leetcode.veryCommon.prefixSum;

import java.util.Arrays;

public class MaximumSumofTwoNonOverlappingSubarrays {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int n = A.length, res = Integer.MIN_VALUE;
        int[] preFixSm = new int[n], lMax = new int[n], mMax = new int[n];
        preFixSm[0] = A[0];
        for (int i = 1; i < n; i++)
            preFixSm[i] = preFixSm[i - 1] + A[i];
        lMax[L - 1] = preFixSm[L - 1];
        mMax[M - 1] = preFixSm[M - 1];
        for (int i = L; i < n; i++)
            lMax[i] = Math.max(lMax[i - 1], preFixSm[i] - preFixSm[i - L]);
        for (int i = M; i < n; i++)
            mMax[i] = Math.max(mMax[i - 1], preFixSm[i] - preFixSm[i - M]);
        Arrays.stream(preFixSm).forEach(System.out::println);
        res = Math.max(preFixSm[n - 1 - M] + lMax[n - 1 - M], preFixSm[n - 1 - L] + mMax[n - 1 - L]);
        for (int i = L + M; i < n; i++) {
            res = Math.max(res, preFixSm[i] - preFixSm[i - M] + lMax[i - M]);
            res = Math.max(res, preFixSm[i] - preFixSm[i - L] + mMax[i - L]);
        }
        return res;
    }
}
