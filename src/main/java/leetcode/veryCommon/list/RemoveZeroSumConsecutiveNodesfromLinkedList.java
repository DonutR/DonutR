package leetcode.veryCommon.list;

import java.util.HashMap;

public class RemoveZeroSumConsecutiveNodesfromLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        HashMap<Integer, ListNode> cache = new HashMap<>();
        ListNode cur = head;
        int sm = 0;
        while (cur != null) {
            sm += cur.val;
            if (sm == 0) {
                head = cur.next;
                cache.clear();
            } else if (!cache.containsKey(sm))
                cache.put(sm, cur);
            else {
                System.out.println(cache);
                ListNode tmp = cache.get(sm).next;
                int tmpSm = sm;
                while (tmp != cur) {
                    tmpSm = tmpSm + tmp.val;
                    cache.remove(tmpSm);
                    tmp = tmp.next;
                }
                cache.get(sm).next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
