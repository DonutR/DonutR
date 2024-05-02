package leetcode.veryCommon.array;

import java.util.*;

public class JumpGame {
    public static void main(String[] args) {
        int[] inp = {1, 1, 1, 0};
        System.out.println(canJump(inp));
    }

    public static boolean canJump1(int[] nums) {
        int endIdx = nums.length - 1;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        q.add(0);
        while (!q.isEmpty()) {
            int curIdx = q.poll();
            int maxIdx = curIdx + nums[curIdx];
            if (maxIdx >= endIdx)
                return true;
            for (int i = curIdx + 1; i <= maxIdx; i++) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    q.add(i);
                }
            }
        }
        return false;
    }

    public static boolean canJump2(int[] nums) {
        int endIdx = nums.length - 1;
        Stack<Integer> q = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        q.add(0);
        while (!q.isEmpty()) {
            int curIdx = q.pop();
            int maxIdx = curIdx + nums[curIdx];
            if (maxIdx >= endIdx)
                return true;
            for (int i = curIdx + 1; i <= maxIdx; i++) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    q.add(i);
                }
            }
        }
        return false;
    }

    public static boolean canJump(int[] nums) {
        int max = 0, i = 0, n = nums.length - 1;
        while (i <= n) {
            max = Math.max(max, i + nums[i]);
            if (max >= n)
                return true;
            i++;
        }
        return false;
    }
}