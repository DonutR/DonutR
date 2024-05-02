package leetcode.veryCommon.list;

/*
Given a linked leetcode.commonList.list, swap every two adjacent nodes and return its head.

        Example:

        Given 1->2->3->4->5->6, you should return the leetcode.commonList.list as 2->1->4->3.
        Note:

        Your algorithm should use only constant extra space.
        You may not modify the values in the leetcode.commonList.list's nodes, only nodes itself may be changed.

Keep counter to identify even nodes.
On reaching even node Swap next of prev node with even node next.
Swap even node next with prev node.
*/
public class SwapNodesInPairs {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs1(ListNode head) {
        return swap(head);
    }

    public ListNode swap(ListNode root) {
        if (root == null)
            return root;
        if (root.next == null) {
            return root;
        }
        if (root.next.next == null) {
            root.next.next = root;
            ListNode temp = root.next;
            root.next = null;
            return temp;
        }
        ListNode temp = swap(root.next.next);
        ListNode temp1 = root.next;
        root.next = temp;
        temp1.next = root;
        return temp1;
    }

    public ListNode swapPairs(ListNode head) {
        return swap(head,null,1);
    }
    public ListNode swap(ListNode root, ListNode prev, int pos) {
        if (root == null)
            return null;
        ListNode ret = swap(root.next, root, pos + 1);
        if (pos % 2 == 0) {
            root.next = prev;
            return ret;
        } else {
            ListNode tmp = root.next;
            root.next = ret;
            return tmp==null?root:tmp;
        }
    }
}
