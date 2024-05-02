package leetcode.veryCommon.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(start);
        q.add(start);
        while (!q.isEmpty()) {
            int idx = q.poll();
            if (arr[idx] == 0)
                return true;
            int a = idx - arr[idx], b = idx + arr[idx];
            if (a >= 0 && !visited.contains(a)) {
                q.add(a);
                visited.add(a);
            }
            if (b < arr.length && !visited.contains(b)) {
                q.add(b);
                visited.add(b);
            }
        }
        return false;
    }
}
