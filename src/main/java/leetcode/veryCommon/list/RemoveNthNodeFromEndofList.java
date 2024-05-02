package leetcode.veryCommon.list;

public class RemoveNthNodeFromEndofList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode ret = new ListNode(-1);
        ret.next = head;
        helper(head, ret, n);
        return ret.next;
    }

    public int helper(ListNode head, ListNode prev, int n) {
        if (head == null)
            return 0;
        else {
            int pos = helper(head.next, head, n);
            pos++;
            if (pos == n)
                prev.next = head.next;
            return pos;
        }
    }
}
