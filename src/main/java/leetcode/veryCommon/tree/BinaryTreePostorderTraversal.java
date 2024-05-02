package leetcode.veryCommon.tree;

import java.util.*;

public class BinaryTreePostorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        if (root != null) stk.push(root);
        LinkedList<Integer> out = new LinkedList<>();
        while (!stk.isEmpty()) {
            root = stk.pop();
            out.addFirst(root.val);
            if (root.left != null) stk.push(root.left);
            if (root.right != null) stk.push(root.right);
        }
        return out;
    }

}
