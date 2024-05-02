package leetcode.veryCommon.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> out = new ArrayList<>();
        recFun(n, k, 1, new ArrayList<>(), 0, out);
        return out;
    }

    public static void recFun(int n, int k, int idx, List<Integer> tmpList, int tmpN, List<List<Integer>> out) {
        if (tmpList.size() == k && tmpN == n) {
            out.add(new ArrayList<>(tmpList));
            return;
        }
        if (tmpN < n && idx < n) {
            tmpList.add(idx);
            recFun(n, k, idx + 1, tmpList, tmpN + idx, out);
            tmpList.remove(tmpList.size() - 1);
            recFun(n, k, idx + 1, tmpList, tmpN, out);
        }
    }
}
