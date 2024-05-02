package leetcode.veryCommon.list;

public class RemoveLinkedListElements {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode ret, prev;
        while (head != null && head.val == val)
            head = head.next;
        ret = head;
        prev = null;
        while (head != null) {
            if (head.val == val)
                prev.next = head.next;
            else
                prev = head;
            head = head.next;
        }
        return ret;
    }
}
