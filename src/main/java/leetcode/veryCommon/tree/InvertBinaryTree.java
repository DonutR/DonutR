package leetcode.veryCommon.tree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode right = invertTree(root.left);
        TreeNode left = invertTree(root.right);
        root.right = right;
        root.left = left;
        return root;
    }

    public TreeNode invertTreeBFS(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
            TreeNode tmpLeft = curr.left;
            curr.left = curr.right;
            curr.right = tmpLeft;
        }
        return root;
    }
}
