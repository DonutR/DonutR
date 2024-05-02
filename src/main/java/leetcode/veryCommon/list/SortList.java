package leetcode.veryCommon.list;

/*Sort a linked leetcode.commonList.list in O(n log n) time using constant space complexity.

        Example 1:

        Input: 4->2->1->3
        Output: 1->2->3->4

        Example 2:

        Input: -1->5->3->4->0
        Output: -1->0->3->4->5
*/

public class SortList {
    public ListNode sortList(ListNode head) {
        if(head==null)
            return head;
        ListNode mid = findMid(head);
        ListNode tmpRight = mid.next;
        mid.next = null;
        ListNode leftHead = mid != head ? sortList(head) : head;
        ListNode rightHead = mid != head ? sortList(tmpRight) : tmpRight;
        return merge(leftHead, rightHead);
    }

    public ListNode findMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode curr = new ListNode(0), head = curr;
        while (left != null && right != null) {
            if (left.val < right.val) {
                curr.next = left;
                curr = left;
                left = left.next;
            } else {
                curr.next = right;
                curr = right;
                right = right.next;
            }
        }
        curr.next = left != null ? left : right;
        return head.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

