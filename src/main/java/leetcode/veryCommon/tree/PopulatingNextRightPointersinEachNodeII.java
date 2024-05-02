package leetcode.veryCommon.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNodeII {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    public Node connect(Node root) {
        recFun(root, new HashMap<>(), 0);
        return root;
    }

    public void recFun(Node root, HashMap<Integer, Node> htMp, int ht) {
        if (root != null) {
            if (htMp.containsKey(ht))
                root.next = htMp.get(ht);
            htMp.put(ht, root);
            recFun(root.right, htMp, ht + 1);
            recFun(root.left, htMp, ht + 1);
        }
    }

    public Node connectBFS(Node root) {
        if (root == null)
            return root;
        Queue<Node> q = new LinkedList<>();
        q.add(null);
        q.add(root);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr == null) {
                Node pre = null;
                for (Node el : q) {
                    if (pre == null) pre = el;
                    else {
                        pre.next = el;
                        pre = el;
                    }
                }
                if (pre != null) pre.next = null;
                if (!q.isEmpty()) q.add(null);
            } else {
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return root;
    }
}
