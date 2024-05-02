package leetcode.veryCommon.tree;

import java.util.Arrays;

public class MaximumDifferenceBetweenNodeandAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    int out = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        out = Integer.MIN_VALUE;
        helper(root, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
        return out;
    }

    public void helper(TreeNode root, int[] minMax) {
        if (root != null) {
            minMax = new int[]{Math.min(minMax[0], root.val), Math.max(minMax[1], root.val)};
            if (root.left == null && root.right == null) {
                out = Math.max(out, Math.abs(minMax[1] - minMax[0]));
            } else {
                helper(root.left, minMax);
                helper(root.right, minMax);
            }
        }
    }
}
