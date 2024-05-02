package leetcode.veryCommon.hardTop50;

import java.util.HashSet;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0)
            return 1;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> surroundNums = new HashSet<>();
        HashSet<Integer> alreadyFoundNums = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1)
                continue;
            if (nums[i] < min)
                min = nums[i];
            if (!alreadyFoundNums.contains(nums[i] - 1) && (nums[i] - 1) > 0) surroundNums.add(nums[i] - 1);
            if (!alreadyFoundNums.contains(nums[i] + 1)) surroundNums.add(nums[i] + 1);
            if (surroundNums.contains(nums[i])) {
                surroundNums.remove(nums[i]);
            }
            alreadyFoundNums.add(nums[i]);
        }
        int surMin = 1;
        if (surroundNums.size() > 0)
            surMin = surroundNums.stream().min((a, b) -> a - b).get();

        return min > 1 ? 1 : surMin;
    }

    //Very intuitive solution
    public int firstMissingPositive(int[] nums, int m) {
        int n = nums.length;
        for (int i = 0; i < n; ++i)
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[i], tmp2 = nums[i] - 1;
                nums[i] = nums[nums[i] - 1];
                nums[tmp2] = tmp;
            }
        for (int i = 0; i < n; ++i)
            if (nums[i] != i + 1)
                return i + 1;

        return n + 1;

    }
}
