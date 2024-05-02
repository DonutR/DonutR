package leetcode.veryCommon.list;

import java.util.LinkedList;
import java.util.List;

public class MergeTwoSortedLists {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        List<ListNode> outList = new LinkedList<>();
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                outList.add(l2);
                l2 = l2.next;
            } else {
                outList.add(l1);
                l1 = l1.next;
            }
        }
        while (l1 != null) {
            outList.add(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            outList.add(l2);
            l2 = l2.next;
        }
        if (outList.size() < 1)
            return null;
        ListNode out = outList.get(0);
        ListNode prev = outList.get(0);
        outList.remove(0);
        for (ListNode n : outList) {
            prev.next = n;
            prev = n;
        }
        return out;
    }

    // iteratively
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode outHead = new ListNode(0), tmpHead = outHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmpHead.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                tmpHead.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            tmpHead = tmpHead.next;
        }
        tmpHead.next = l1 != null ? l1 : l2;
        return outHead.next;
    }

    //recursively
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}