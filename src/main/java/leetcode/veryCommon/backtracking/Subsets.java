package leetcode.veryCommon.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new LinkedList<>();
        Arrays.sort(nums);
        helper(0, nums, new ArrayList<>(), out);
        return out;
    }

    public void helper(int start, int[] nums, ArrayList<Integer> currList, List<List<Integer>> out) {
        out.add(new ArrayList<>(currList));
        for (int i = start; i < nums.length; i++) {
            currList.add(nums[i]);
            helper(i + 1, nums, currList, out);
            currList.remove(currList.size() - 1);
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> out = new LinkedList<>();
        for (int len = 0; len < nums.length + 1; len++)
            helper(0, len, new ArrayList<Integer>(), nums, out);
        return out;
    }

    public void helper(int first, int len, ArrayList<Integer> curr, int[] num, List<List<Integer>> out) {
        // if the combination is done
        if (curr.size() == len) {
            out.add(new ArrayList<>(curr));
            return;
        }
        for (int i = first; i < num.length; i++) {
            // add i into the current combination
            curr.add(num[i]);
            // use next integers to complete the combination
            helper(i + 1, len, curr, num, out);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> out = new LinkedList<>();
        helper(out, new ArrayList<Integer>(), 0, nums);
        return out;
    }

    public void helper(List<List<Integer>> out, List<Integer> curList, int idx, int[] nums) {
        if (idx == nums.length) {
            out.add(new ArrayList<>(curList));
        } else {
            helper(out, curList, idx + 1, nums);
            curList.add(nums[idx]);
            helper(out, curList, idx + 1, nums);
            curList.remove(curList.size() - 1);
        }
    }
}
