package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int n = nums.length / 2;
        HashMap<Integer, Integer> ctMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            ctMap.put(a, ctMap.getOrDefault(a, 0) + 1);
            if (ctMap.get(a) > n)
                return a;
        }
        return -1;
    }
    //Boyer-Moore Voting Algorithm
    //If we had some way of counting instances of the majority element as +1+1 and instances of any other element as -1−1,
    // summing them would make it obvious that the majority element is indeed the majority element.
    //Example : [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    //Example : [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 5, 5, 5, 5]

    public int majorityElement2(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
