package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        int output = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!visited.contains(i)) {
                int before = visited.size();
                helper(i, visited, nums);
                int after = visited.size();
                output = Math.max(output, after - before);
            }
        }
        return output;
    }

    public void helper(int i, Set<Integer> visited, int[] nums) {
        visited.add(i);
        if (!visited.contains(nums[i]))
            helper(nums[i], visited, nums);
    }
}
