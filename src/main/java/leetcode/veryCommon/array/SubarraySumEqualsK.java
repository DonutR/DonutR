package leetcode.veryCommon.array;

import java.util.HashMap;

public class SubarraySumEqualsK {
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }


    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> smCt = new HashMap<>();
        smCt.put(0, 1);
        int count = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum - k;
            if (smCt.containsKey(rem))
                count += smCt.get(rem);
            smCt.put(sum, smCt.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
