package leetcode.veryCommon.random;

import scala.Int;

import java.util.*;
import java.util.stream.Collectors;

public class FourSum {
    public static void main(String[] args) {
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        System.out.println(fourSum(nums, 0));
    }

    //n^4
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target)
                            out.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[k], nums[l]}));
                        else if (sum > target)
                            break;
                        while (l + 1 < nums.length && nums[l + 1] == nums[l])
                            l++;
                    }
                    while (k + 1 < nums.length && nums[k + 1] == nums[k])
                        k++;
                }
                while (j + 1 < nums.length && nums[j + 1] == nums[j])
                    j++;
            }
            while (i + 1 < nums.length && nums[i + 1] == nums[i])
                i++;
        }
        return out;
    }

    //2*n^2
    public static List<List<Integer>> fourSumOpt(int[] nums, int target) {
        HashMap<Integer, List<int[]>> memo = new HashMap<>();
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);

        for (int k = 0; k < nums.length; k++) {
            for (int l = k + 1; l < nums.length; l++) {
                int sum = nums[k] + nums[l];
                memo.computeIfAbsent(sum, x -> new LinkedList<>()).add(new int[]{k, l});
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = target - nums[i] - nums[j];
                if (memo.containsKey(sum)) {
                    int mxIdx = j;
                    Set<String> uqSet = memo.get(sum).stream()
                            .filter(kl -> Math.min(kl[0], kl[1]) > mxIdx)
                            .map(a -> nums[a[0]] + " " + nums[a[1]])
                            .collect(Collectors.toSet());
                    for (String kl : uqSet) {
                        String[] tmKl = kl.split(" ");
                        out.add(Arrays.asList(new Integer[]{nums[i], nums[j], Integer.parseInt(tmKl[0]), Integer.parseInt(tmKl[1])}));
                    }
                }
                while (j + 1 < nums.length && nums[j + 1] == nums[j])
                    j++;
            }
            while (i + 1 < nums.length && nums[i + 1] == nums[i])
                i++;
        }
        return out;
    }
}
