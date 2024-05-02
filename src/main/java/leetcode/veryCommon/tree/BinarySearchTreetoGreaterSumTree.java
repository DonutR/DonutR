package leetcode.veryCommon.tree;

public class BinarySearchTreetoGreaterSumTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int curSum = 0;

    public TreeNode bstToGst(TreeNode root) {
        bstToGstHelper(root);
        return root;
    }

    public void bstToGstHelper(TreeNode root) {
        if (root != null) {
            bstToGstHelper(root.right);
            curSum += root.val;
            root.val = curSum;
            bstToGstHelper(root.left);
        }
    }
}
