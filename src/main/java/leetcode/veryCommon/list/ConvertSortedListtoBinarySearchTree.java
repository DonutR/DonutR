package leetcode.veryCommon.list;

public class ConvertSortedListtoBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        return recFun(head);
    }

    public TreeNode recFun(ListNode first) {
        if (first == null)
            return null;

        ListNode[] mid = getMid(first);
        TreeNode node = new TreeNode(mid[1].val);
        if (mid[0] != null) {
            mid[0].next = null;
            node.left = recFun(first);
            node.right = recFun(mid[1].next);
        }
        return node;
    }

    public ListNode[] getMid(ListNode first) {
        ListNode slow = first, fast = first, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        ListNode[] ret = {prev, slow};
        return ret;
    }

}
