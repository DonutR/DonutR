package leetcode.veryCommon.list;

import java.util.Stack;

public class AddTwoNumbersII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> l1Stack = new Stack<>();
        Stack<ListNode> l2Stack = new Stack<>();

        while (l1 != null) {
            l1Stack.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2Stack.push(l2);
            l2 = l2.next;
        }
        int rem = 0;
        ListNode head = new ListNode(0);
        while (!l2Stack.isEmpty() || !l1Stack.isEmpty()) {
            int sm = rem + (!l2Stack.isEmpty() ? l2Stack.pop().val : 0) + (!l1Stack.isEmpty() ? l1Stack.pop().val : 0);
            rem = sm / 10;
            ListNode tail = new ListNode(sm % 10);
            tail.next = head.next;
            head.next = tail;
        }
        if (rem != 0)
            head.val = rem;
        return head.val > 0 ? head : head.next;
    }
}
