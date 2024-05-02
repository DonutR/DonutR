package random;

import leetcode.veryCommon.tree.BinaryTreeVerticalOrderTraversal;
import scala.Array;
import scala.Int;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Solution {
    public int[] getNumberOfBacklogOrders(int[] offsets) {
        int head = -1;
        Queue<Integer> q = new PriorityQueue<>();
        int[] output = new int[offsets.length];
        for (int i = 0; i < offsets.length; i++) {
            int offset = offsets[i];
            q.add(offset);
            while (!q.isEmpty() && head == q.peek()) {
                head++;
                q.poll();
            }
            output[i] = head;
        }
        return output;
    }
}
