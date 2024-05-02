package leetcode.veryCommon.tree;

public class DistributeCoinsinBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int out = 0;

    public int distributeCoins(TreeNode root) {
        out = 0;
        helper(root);
        return out;
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int newVal = root.val + left + right;
        out += Math.abs(left) + Math.abs(right);
        return newVal - 1;
    }
}
