package leetcode.veryCommon.list;

//Let's say we have a linked list consisting of three different nodes, A → B → C and we want to reverse the links between the nodes and obtain A ← B ← C.
//
//Suppose we have at our disposal, two pointers. One of them points to the node A and the other one points to the node B. Let's call these pointers prev and cur respectively. We can simply use these two pointers to reverse the link between A and B.
//
//cur.next = prev
//The only problem with this is, we don't have a way of progressing further i.e. once we do this, we can't reach the node C. That's why we need a third pointer that will help us continue the link reversal process. So, we do the following instead.
//
//third = cur.next
//cur.next = prev
//prev = cur
//cur = third
public class ReverseLinkedListII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode prev = null, curr = head, tail = null;
        int index = 1;
        while (curr != null) {
            if (index >= m && index <= n) {
                ListNode tmpNxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmpNxt;
            } else if (index < m) {
                prev = curr;
                tail = curr;
                curr = curr.next;
            } else if (index > n)
                break;
            index++;
        }
        if (tail == null) {
            head.next = curr;
            return prev;
        } else {
            ListNode tmpTail = tail.next;
            tail.next = prev;
            tmpTail.next = curr;
            return head;
        }
    }

    ListNode after = null;
    ListNode last = null;
    ListNode headRet = null;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        headRet = head;
        if (m < n) helper(head, null, m, n, 1);
        return headRet;
    }

    public void helper(ListNode curr, ListNode prev, int m, int n, int i) {
        if (i < m)
            helper(curr.next, curr, m, n, ++i);
        else if (i == m) {
            helper(curr.next, curr, m, n, ++i);
            curr.next = after;
            if (prev != null)
                prev.next = last;
            else
                headRet = last;
        } else if (i > m && i < n) {
            ListNode tmpNext = curr.next;
            curr.next = prev;
            helper(tmpNext, curr, m, n, ++i);
        } else if (i == n) {
            after = curr.next;
            last = curr;
            curr.next = prev;
        }
    }
}
