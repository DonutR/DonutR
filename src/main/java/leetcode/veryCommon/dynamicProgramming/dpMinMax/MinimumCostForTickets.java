package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;

public class MinimumCostForTickets {
    int[] costs;
    Integer[] memo;
    Set<Integer> daysSet = new HashSet<>();

    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        this.memo = new Integer[366];
        for (int d : days) daysSet.add(d);
        return dp(1);
    }

    public int dp(int day) {
        if (day > 365)
            return 0;
        if (memo[day] != null)
            return memo[day];
        int ans;
        if (daysSet.contains(day)) {
            ans = Math.min(dp(day + 1) + costs[0], dp(day + 7) + costs[1]);
            ans = Math.min(ans, dp(day + 30) + costs[2]);
        } else ans = dp(day + 1);
        memo[day] = ans;
        return ans;
    }
}
