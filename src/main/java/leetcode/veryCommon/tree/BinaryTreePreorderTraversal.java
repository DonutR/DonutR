package leetcode.veryCommon.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        if (root != null) stk.push(root);
        List<Integer> out = new ArrayList<>();
        while (!stk.isEmpty()) {
            root = stk.pop();
            out.add(root.val);
            if (root.right != null) stk.push(root.right);
            if (root.left != null) stk.push(root.left);
        }
        return out;
    }
}
