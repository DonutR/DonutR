package leetcode.veryCommon.tree;

import java.util.LinkedList;
import java.util.Queue;

//You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
//
//        struct Node {
//        int val;
//        Node *left;
//        Node *right;
//        Node *next;
//        }
//        Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
//        Initially, all next pointers are set to NULL.
//
//
//
//        Follow up:
//
//        You may only use constant extra space.
//        Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
//
//
//        Example 1:
//
//
//
//        Input: root = [1,2,3,4,5,6,7]
//        Output: [1,#,2,3,#,4,5,6,7,#]
//        Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
//
//
//        Constraints:
//
//        The number of nodes in the given tree is less than 4096.
//        -1000 <= node.val <= 1000
public class PopulatingNextRightPointersInEachNode {
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

    public Node connect1(Node root) {
        if (root != null) {
            Queue<Node> bfsQueue = new LinkedList<>();
            bfsQueue.add(root);
            bfsQueue.add(null);
            while (!bfsQueue.isEmpty()) {
                Node curNode = bfsQueue.poll();
                Node rightNode = bfsQueue.peek();
                if (curNode != null) {
                    curNode.next = rightNode;
                    if (curNode.left != null) bfsQueue.add(curNode.left);
                    if (curNode.right != null) bfsQueue.add(curNode.right);
                } else if (curNode == null && !bfsQueue.isEmpty())
                    bfsQueue.add(null);
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if (root == null)
            return root;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }
}
