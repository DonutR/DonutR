package leetcode.veryCommon.hardTop50;

public class BinaryTreeMaximumPathSum {
    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        treeSumDfs(root);
        return maxSum;
    }

    public static int treeSumDfs(TreeNode root) {
        if (root != null) {
            int l = treeSumDfs(root.left);
            int r = treeSumDfs(root.right);
            int lrMax = Math.max(l, r);
            int ret = Math.max(root.val + lrMax, root.val);
            maxSum = Math.max(maxSum, ret);
            maxSum = Math.max(maxSum, root.val + l + r);
            return ret;
        }
        return 0;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
