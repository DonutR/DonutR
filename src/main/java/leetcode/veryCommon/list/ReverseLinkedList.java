package leetcode.veryCommon.list;

public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //recursively
    ListNode headOut;

    public ListNode reverseList2(ListNode head) {
        headOut = null;
        helper2(head, null);
        return headOut;
    }

    public void helper2(ListNode head, ListNode prev) {
        if (head != null) {
            helper(head.next, head);
            head.next = prev;
        } else {
            headOut = prev;
        }
    }

    //recursively
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        return helper(head, null);
    }
    public ListNode helper(ListNode head, ListNode prev) {
        if (head.next == null) {
            head.next = prev;
            return head;
        } else {
            ListNode ret = helper(head.next, head);
            head.next = prev;
            return ret;
        }
    }

    //Iterative
    public ListNode reverseList3(ListNode head) {
        if (head == null) return null;
        ListNode prev = null;
        while (head.next != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        head.next = prev;
        return head;
    }
}
