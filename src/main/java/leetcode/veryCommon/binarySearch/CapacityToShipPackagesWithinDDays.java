package leetcode.veryCommon.binarySearch;

public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, needShip = 1, curWeight = 0;
            for (int w : weights) {
                if (curWeight + w > mid) {
                    needShip++;
                    curWeight = 0;
                }
                curWeight += w;
            }
            if (needShip > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
