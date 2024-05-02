package leetcode.veryCommon.random;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> out = new ArrayList<>();
        HashSet<String> dupCheck = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            List<List<Integer>> outTmp = twoSum(nums, target, i);
            for (List<Integer> tS : outTmp) {
                tS.add(nums[i]);
                tS.sort((a, b) -> a - b);
                if (!dupCheck.contains(tS.toString())) {
                    out.add(tS);
                    dupCheck.add(tS.toString());
                }
            }
        }
        System.out.println(out.toString());
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> out = new ArrayList<>();
        HashSet<String> dupCheck = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            List<List<Integer>> outTmp = twoSum(nums, target, i);
            for (List<Integer> tS : outTmp) {
                tS.add(nums[i]);
                tS.sort((a, b) -> a - b);
                if (!dupCheck.contains(tS.toString())) {
                    out.add(tS);
                    dupCheck.add(tS.toString());
                }
            }
        }
        return out;
    }

    public static List<List<Integer>> twoSum(int[] nums, int target, int skipInd) {
        HashSet<Integer> sumLeftMap = new HashSet<>();
        List<List<Integer>> out = new ArrayList<>();
        for (int i = skipInd + 1; i < nums.length; i++) {
            int diff = target - nums[i];
            if (sumLeftMap.contains(diff)) {
                List<Integer> tmoOut = new ArrayList<>();
                tmoOut.add(diff);
                tmoOut.add(nums[i]);
                out.add(tmoOut);
            } else {
                sumLeftMap.add(nums[i]);
            }
        }
        return out;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, HashSet<Integer>> valIdx = new HashMap<>();
        HashSet<String> outSet = new HashSet<>();
        List<List<Integer>> out = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 3)
            return out;

        for (int i = 0; i < nums.length; i++) valIdx.put(nums[i], new HashSet<>());
        for (int i = 0; i < nums.length; i++) valIdx.get(nums[i]).add(i);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (j - 1 > 1 && nums[i] == nums[j]) continue;
                int sm = -1 * (nums[i] + nums[j]);
                if (valIdx.containsKey(sm)) {
                    for (int k : valIdx.get(sm)) {
                        if (i != k && j != k) {
                            int[] outTmp = {nums[i], nums[j], nums[k]};
                            outSet.add(Arrays.stream(outTmp).sorted().boxed()
                                    .collect(Collectors.toList())
                                    .stream()
                                    .map(a -> a + "")
                                    .collect(Collectors.joining(",")));
                        }
                    }
                }
            }
        }
        outSet.stream().forEach(s -> {
            out.add(Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        });
        return out;
    }
}
