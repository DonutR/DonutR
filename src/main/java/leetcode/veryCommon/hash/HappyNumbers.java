package leetcode.veryCommon.hash;

import java.util.HashSet;

/**
 * Write an algorithm to determine if a number is "happy".
 * <p>
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Example:
 * <p>
 * Input: 18023
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumbers {
    public static void main(String[] args) {
        System.out.println(isHappy2(213));
    }

    public static boolean isHappy2(int n) {
        return helper(n, new HashSet<>());
    }

    public static boolean helper(int n, HashSet<Integer> prevInt) {
        int sum = 0;
        while (n > 0) {
            int rem = n % 10;
            sum = sum + rem * rem;
            n = n / 10;
        }
        System.out.println(sum);
        if (sum == 1)
            return true;
        else if (prevInt.contains(sum))
            return false;
        else {
            prevInt.add(sum);
            return helper(sum, prevInt);
        }
    }

    public boolean isHappy(int n) {
        HashSet<Integer> prevInt = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n > 0) {
                int rem = n % 10;
                sum = sum + rem * rem;
                n = n / 10;
            }
            if (sum == 1)
                return true;
            else if (prevInt.contains(sum))
                return false;
            else {
                prevInt.add(sum);
                n = sum;
            }
        }
    }
}
