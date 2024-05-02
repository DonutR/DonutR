package leetcode.veryCommon.list;

public class PalindromeLinkedList {
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

    ListNode mid = null;
    boolean isEven = false;

    public boolean isPalindrome(ListNode head) {
        mid = null;
        isEven = false;
        if (head == null)
            return true;
        ListNode fast = head.next, slow = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null)
                fast = fast.next.next;
            else {
                isEven = true;
                fast = null;
            }
        }
        mid = slow;
        return helper(head);
    }

    public boolean helper(ListNode head) {
        if (head != mid) {
            boolean ret = helper(head.next);
            boolean cur = head.val == mid.val;
            mid = mid.next;
            return ret && cur;
        }
        if (!isEven) mid = mid.next;
        return true;
    }
}
