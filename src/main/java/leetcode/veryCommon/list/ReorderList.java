package leetcode.veryCommon.list;

import java.util.ArrayList;

public class ReorderList {
    //Find mid point
    //Use arrayList to store second half
    //Merge first half and array in reverse order
    public void reorderList2(ListNode head) {
        if (head == null)
            return;
        ArrayList<ListNode> out = new ArrayList<>();
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode midStart = slow.next;
        while (midStart != null) {
            out.add(midStart);
            midStart = midStart.next;
        }
        int i = out.size() - 1;
        ListNode curr = head;
        while (curr != slow && i >= 0) {
            ListNode tmp = curr.next;
            curr.next = out.get(i);
            out.get(i).next = tmp;
            curr = tmp;
            i--;
        }
        slow.next = null;
    }

    //Find mid point
    //Reverse second half
    //Merge two half
    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode midStart = slow.next;
        ListNode midRevHead = inPlaceReverse(midStart, null);
        slow.next = null;
        ListNode curr = head;
        while (midRevHead != null && curr != null) {
            ListNode tmp = curr.next;
            ListNode tmp2 = midRevHead.next;
            curr.next = midRevHead;
            midRevHead.next = tmp;
            curr = tmp;
            midRevHead = tmp2;
        }
    }

    public ListNode inPlaceReverse(ListNode curr, ListNode prev) {
        if (curr != null) {
            ListNode head = inPlaceReverse(curr.next, curr);
            curr.next = prev;
            return head;
        }
        return prev;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
