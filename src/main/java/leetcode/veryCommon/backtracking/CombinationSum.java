package leetcode.veryCommon.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        //no need to sort since the input is unique
        List<List<Integer>> output = new ArrayList<>();
        helper(candidates, target, 0, output, new LinkedList<>());
        return output;
    }

    public void helper(int[] nums, int target, int sum, List<List<Integer>> output, LinkedList<Integer> path) {
        if (target == sum) {
            output.add(new ArrayList<>(path));
            return;
        } else if (sum > target)
            return;
        else {
            for (int j = 0; j < nums.length; j++) {
                if (path.size() == 0 || (path.getLast() < nums[j])) {
                    System.out.println(path);
                    path.add(nums[j]);
                    helper(nums, target, sum + nums[j], output, path);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
