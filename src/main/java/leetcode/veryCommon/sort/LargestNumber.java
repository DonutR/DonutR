package leetcode.veryCommon.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LargestNumber {
    public static void main(String[] args) {
        int[] inp = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(inp));
    }

    public static String largestNumber(int[] nums) {
        String out = Arrays.stream(nums).boxed()
                .sorted((a, b) -> {
                    String ab = a + "" + b;
                    String ba = b + "" + a;
                    return ba.compareTo(ab);
                }).map(a -> a + "").collect(Collectors.joining());
        return out.charAt(0) == '0' ? "0" : out;
    }
}
