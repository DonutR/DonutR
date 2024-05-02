package leetcode.veryCommon.stackMonotoneStackQueue;


import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class GenericImplementation {
    public static void main(String[] args) {
        int[] inp = {1, 3, 4, 2, 5};
        Arrays.stream(greaterElIdx(inp, true)).forEach(a -> System.out.print(a + " "));
        System.out.println();

        Arrays.stream(greaterElIdx(inp, false)).forEach(a -> System.out.print(a + " "));
        System.out.println();

        Arrays.stream(lesserElIdx(inp, true)).forEach(a -> System.out.print(a + " "));
        System.out.println();

        Arrays.stream(lesserElIdx(inp, false)).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    public static int[] greaterElIdx(int[] nums, boolean isLeftRight) {
        int n = nums.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < n; j++) {
            int i = isLeftRight ? j : n - 1 - j;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int idx = stack.pop();
                out[idx] = i;
            }
            stack.push(i);
        }
        return out;
    }

    public static int[] lesserElIdx(int[] nums, boolean isLeftRight) {
        int n = nums.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < n; j++) {
            int i = isLeftRight ? j : n - 1 - j;
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int idx = stack.pop();
                out[idx] = i;
            }
            stack.push(i);
        }
        return out;
    }
}
