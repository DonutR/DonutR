package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;

public class PaintHouse {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[] tillPrevHouseMin = costs[0].clone();
        for (int i = 1; i < n; i++) {
            int[] tmpPrevHouseMin = new int[3];
            tmpPrevHouseMin[0] = Math.min(tillPrevHouseMin[1] + costs[i][0], tillPrevHouseMin[2] + costs[i][0]);
            tmpPrevHouseMin[1] = Math.min(tillPrevHouseMin[0] + costs[i][1], tillPrevHouseMin[2] + costs[i][1]);
            tmpPrevHouseMin[2] = Math.min(tillPrevHouseMin[0] + costs[i][2], tillPrevHouseMin[1] + costs[i][2]);
            tillPrevHouseMin = tmpPrevHouseMin;
        }
        return Math.min(Math.min(tillPrevHouseMin[0], tillPrevHouseMin[1]), tillPrevHouseMin[2]);
    }
}
