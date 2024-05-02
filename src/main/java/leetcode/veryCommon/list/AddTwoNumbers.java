package leetcode.veryCommon.list;

public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb = new StringBuilder();
        int rem = 0;
        ListNode outHead = null;
        ListNode tmpHead = null;
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + rem;
            rem = sum / 10;
            if (outHead == null) {
                outHead = new ListNode(sum % 10);
                tmpHead = outHead;
            } else {
                tmpHead.next = new ListNode(sum % 10);
                tmpHead = tmpHead.next;
            }
            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);
        }
        if (rem != 0) {
            if (outHead == null) {
                outHead = new ListNode(rem);
            } else {
                tmpHead.next = new ListNode(rem);
            }
        }
        return outHead;
    }
}
