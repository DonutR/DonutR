package leetcode.veryCommon.stackMonotoneStackQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 * <p>
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] inp) {
        int[] out = new int[inp.length];
        int n = inp.length;
        Arrays.fill(out, -1);
        Stack<Integer> s = new Stack<>();
        for (int k = 0; k < 2 * inp.length; k++) {
            int i = (k >= n ? k - n : k);
            while (!s.isEmpty() && inp[s.peek()] < inp[i])
                out[s.pop()] = inp[i];
            s.push(i);
        }
        return out;
    }
}
