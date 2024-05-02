package leetcode.veryCommon.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        recFun(list, new HashSet<Integer>(), new ArrayList<Integer>(), nums);
        return list;
    }

    public static void recFun(List<List<Integer>> outList, Set<Integer> tmpSet, List<Integer> tmpList, int[] num) {
        if (tmpList.size() == num.length) {
            outList.add(new ArrayList<>(tmpList));
        } else {
            for (int i = 0; i < num.length; i++) {
                if (!tmpSet.contains(num[i])) {
                    tmpList.add(num[i]);
                    tmpSet.add(num[i]);
                    recFun(outList, tmpSet, tmpList, num);
                    tmpList.remove(tmpList.size() - 1);
                    tmpSet.remove(num[i]);
                }
            }
        }
    }

}
