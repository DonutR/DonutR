package leetcode.veryCommon.twoPointer;

import java.util.LinkedList;
import java.util.List;

public class SquaresofaSortedArray {
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] out = new int[N];
        int posIdx = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] >= 0) {
                posIdx = i;
                break;
            }
        }
        int i = 0;
        int negIdx = posIdx - 1;
        while (posIdx < N && negIdx >= 0) {
            int posPower = A[posIdx] * A[posIdx];
            int negPower = A[negIdx] * A[negIdx];
            if (posPower < negPower) {
                out[i] = posPower;
                posIdx++;
            } else {
                out[i] = negPower;
                negIdx--;
            }
            i++;
        }

        while (negIdx >= 0) {
            int negPower = A[negIdx] * A[negIdx];
            out[i] = negPower;
            negIdx--;
            i++;
        }
        while (posIdx < N) {
            int posPower = A[posIdx] * A[posIdx];
            out[i] = posPower;
            posIdx++;
            i++;
        }
        return out;
    }

    public int[] sortedSquares2(int[] nums) {
        int i = 0, j = 0, ct = 0;
        int[] out = new int[nums.length];
        for (; i < nums.length; i++) {
            if (nums[i] >= 0)
                break;
        }
        j = i - 1;
        for (; i < nums.length && j >= 0; ) {
            int a = nums[i] * nums[i], b = nums[j] * nums[j];
            if (a < b) {
                out[ct] = a;
                i++;
            } else {
                out[ct] = b;
                j--;
            }
            ct++;
        }
        for (; i < nums.length; i++, ct++)
            out[ct] = nums[i] * nums[i];
        for (; j >= 0; j--, ct++)
            out[ct] = nums[j] * nums[j];
        return out;
    }
}
