package leetcode.veryCommon.list;

import java.util.HashSet;

public class IntersectionofTwoLinkedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        HashSet<ListNode> map = new HashSet<>();
        ListNode tmpA = headA;
        ListNode tmpB = headB;

        while (tmpA != null) {
            map.add(tmpA);
            tmpA = tmpA.next;
        }
        while (tmpB != null) {
            if (map.contains(tmpB)) {
                return tmpB;
            }
            tmpB = tmpB.next;
        }
        return null;
    }

    //Two Pointer
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpA = headA;
        ListNode tmpB = headB;

        int runct = 1;
        while (runct <= 2) {
            if (tmpA == tmpB)
                return tmpA;
            if (tmpA == null) {
                tmpA = headB;
                runct++;
            } else tmpA = tmpA.next;
            if (tmpB == null) tmpB = headA;
            else tmpB = tmpB.next;
        }
        return null;
    }
}
