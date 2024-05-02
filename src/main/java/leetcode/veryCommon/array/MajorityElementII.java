package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://gregable.com/
//Majority Voting Algorithm
//Boyer-Moore Algorithm
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = Integer.MIN_VALUE, candidate2 = Integer.MIN_VALUE, count1 = 0, count2 = 0;
        List<Integer> out = new LinkedList<>();
        for (int n : nums) {
            if (n == candidate1)
                count1++;
            else if (n == candidate2)
                count2++;
            else if (count1 == 0) {
                candidate1 = n;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        for (int can : new int[]{candidate1, candidate2}) {
            if (can != Integer.MIN_VALUE)
                if (Arrays.stream(nums).filter(n -> n == can).count() > nums.length / 3)
                    out.add(can);
        }
        return out;
    }
}
