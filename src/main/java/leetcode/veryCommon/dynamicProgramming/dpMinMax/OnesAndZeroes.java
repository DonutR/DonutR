package leetcode.veryCommon.dynamicProgramming.dpMinMax;

public class OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        return calculate(strs, 0, m, n, memo);
    }

    public int calculate(String[] strs, int i, int zero, int one, int[][][] memo) {
        if (i == strs.length)
            return 0;
        else if (memo[i][zero][one] != 0)
            return memo[i][zero][one];
        int[] count = getZeroOneCt(strs[i]);
        int taken = -1;
        if (zero - count[0] >= 0 && one - count[1] >= 0)
            taken = calculate(strs, i + 1, zero - count[0], one - count[1], memo) + 1;
        int not_taken = calculate(strs, i + 1, zero, one, memo);
        memo[i][zero][one] = Math.max(taken, not_taken);
        return memo[i][zero][one];
    }

    public int[] getZeroOneCt(String str) {
        int[] out = new int[2];
        for (char c : str.toCharArray()) {
            if (c == '0')
                out[0]++;
            else out[1]++;

        }
        return out;
    }
}
