package leetcode.veryCommon.slidingWindow;

public class GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length, happyCustCt = 0, sumI = 0, sumJ = 0, i = 0, j = 0, max;

        for (int k = 0; k < n; k++)
            if (grumpy[k] == 0) happyCustCt += customers[k];

        for (; i < X; i++)
            if (grumpy[i] == 1) sumI += customers[i];

        max = sumI;
        for (; i < n; i++, j++) {
            if (grumpy[i] == 1) sumI += customers[i];
            if (grumpy[j] == 1) sumJ += customers[j];
            max = Math.max(max, sumI - sumJ);
        }
        return happyCustCt + max;
    }
}
