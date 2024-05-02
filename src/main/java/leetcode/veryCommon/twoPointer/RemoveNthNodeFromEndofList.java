package leetcode.veryCommon.twoPointer;

public class RemoveNthNodeFromEndofList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode nthNode = head;
        ListNode del = head;
        for (int i = 0; i < n; i++) {
            nthNode = nthNode.next;
        }
        if (nthNode == null)
            return head.next;
        while (nthNode.next != null) {
            nthNode = nthNode.next;
            del = del.next;
        }
        del.next = del.next.next;
        return head;
    }
}
