package leetcode.veryCommon.dynamicProgramming.dpMinMax;

import java.util.List;

public class Triangle {
    public static int min = Integer.MAX_VALUE;

    //Normal recoursion Top Down and Bottom Up
    public int minimumTotal2(List<List<Integer>> triangle) {
//        int[][] memo = new int[triangle.size()][triangle.size()];
//        System.out.println(recFun(triangle, 0, 0, memo));
//        return recFun(triangle, 0, 0, memo);
//        min = Integer.MAX_VALUE;
//        recFun(triangle, 0, 0, 0);
//        return min;
        return 0;
    }

    //DP accepted
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j + 1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    //bottom up memorization + greedy
    public static int recFun(List<List<Integer>> triangle, int idx, int ht, int[][] memo) {
        if (ht == triangle.size())
            return 0;
        if (memo[idx][ht] > 0)
            return memo[idx][ht];
        int ans = Integer.MAX_VALUE;
        if (idx + 1 < triangle.size() && triangle.get(ht).get(idx) < triangle.get(ht).get(idx + 1)) {
            ans = Math.min(ans, recFun(triangle, idx, ht + 1, memo));
            ans = Math.min(ans, recFun(triangle, idx + 1, ht + 1, memo));
        } else {
            ans = Math.min(ans, recFun(triangle, idx + 1, ht + 1, memo));
            ans = Math.min(ans, recFun(triangle, idx, ht + 1, memo));
        }
        memo[idx][ht] = ans;
        return ans + triangle.get(ht).get(idx);
    }

    //Top Down memorization + greedy
    public static void recFun(List<List<Integer>> triangle, int sum, int idx, int ht) {
        if (ht == triangle.size()) {
            if (sum < min)
                min = sum;
            return;
        }
        sum = sum + triangle.get(ht).get(idx);
        recFun(triangle, sum, idx, ht + 1);
        recFun(triangle, sum, idx + 1, ht + 1);
    }
}
