package leetcode.veryCommon.list;

import java.util.List;

public class RemoveDuplicatesfromSortedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode out = head;
        ListNode prev = new ListNode(Integer.MIN_VALUE);
        while (head != null) {
            while (head != null && prev.val == head.val)
                head = head.next;
            prev.next = head;
            prev = head;
            if (head != null) head = head.next;
        }
        return out;
    }
}
