package leetcode.veryCommon.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ShuffleAnArray {
    class Solution {
        int[] nums;
        Random rd = new Random();

        public Solution(int[] nums) {
            this.nums = nums;
            rd = new Random();
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return nums;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            Supplier<ArrayList<Integer>> arrayList = () -> new ArrayList<Integer>();
            ArrayList<Integer> tmpList = Arrays.stream(nums).boxed().collect(Collectors.toCollection(arrayList));
            int[] out = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                int randIdx = rd.nextInt(tmpList.size());
                out[i] = tmpList.get(randIdx);
                tmpList.remove(randIdx);
            }
            return out;
        }
    }
}
