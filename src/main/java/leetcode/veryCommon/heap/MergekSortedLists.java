package leetcode.veryCommon.heap;

import java.util.*;

public class MergekSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode outHead = new ListNode(0), tmpHead = outHead;

        Arrays.stream(lists).filter(a -> a != null).forEach(a -> q.add(a));

        if (q.isEmpty())
            return null;

        while (!q.isEmpty()) {
            ListNode n = q.poll();
            tmpHead.next = new ListNode(n.val);
            tmpHead = tmpHead.next;
            if (n.next != null)
                q.offer(n.next);
        }
        return outHead.next;
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        ListNode resHead, head = new ListNode(0);
        resHead = head;
        Queue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        Arrays.stream(lists).filter(l -> l != null).forEach(l -> q.add(l));
        while (!q.isEmpty()) {
            ListNode next = q.poll();
            head.next = next;
            head = next;
            if (next.next != null)
                q.offer(next.next);
        }
        return resHead.next;
    }
}
