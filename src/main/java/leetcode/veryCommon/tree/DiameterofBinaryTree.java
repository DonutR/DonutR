package leetcode.veryCommon.tree;

public class DiameterofBinaryTree {
    public int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }

    public int helper(TreeNode root) {
        if (root != null) {
            int a = helper(root.left);
            int b = helper(root.right);
            max = Math.max(max, a + b);
            return Math.max(a, b) + 1;
        }
        return 0;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
