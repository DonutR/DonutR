package leetcode.veryCommon.hardTop50;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReverseNodesinkGroup {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode outHead = null;
        List<ListNode[]> ret = new ArrayList<>();
        ret.add(new ListNode[]{null, head});
        while (ret.get(ret.size() - 1)[1] != null) ;
        {
            ListNode[] tmp = ret.get(ret.size() - 1);
            ret.add(helper(tmp[1], k, tmp[0]));
        }
        outHead = ret.get(1)[1];
        int i = 0, j = 2;
        for (; i < ret.size() - 2; i++, j++)
            ret.get(i)[1].next = ret.get(j)[0];
        ret.get(i)[1].next = ret.get(j)[1];
        return outHead;
    }

    public ListNode[] helper(ListNode root, int ct, ListNode prev) {
        System.out.println(root.val);
        ListNode[] ret = new ListNode[2];
        if (ct == 0 || root == null) {
            return new ListNode[]{prev, root};
        } else {
            ret = helper(root.next, ct - 1, root);
            if (ret != null)
                root.next = prev;
            return ret;
        }
    }
}
