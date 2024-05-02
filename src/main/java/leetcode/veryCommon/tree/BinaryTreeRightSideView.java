package leetcode.veryCommon.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        helper(root, out,0);
        return out;
    }

    public void helper(TreeNode root, List<Integer> out, int height) {
        if (root != null) {
            if (height == out.size())
                out.add(root.val);
            helper(root.right, out, height + 1);
            helper(root.left, out, height + 1);
        }
    }
}
