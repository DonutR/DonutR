package leetcode.veryCommon.tree;

public class FlattenBinaryTreetoLinkedList {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void flatten(TreeNode root) {
        helper(root);
    }

    public TreeNode helper(TreeNode root) {
        if (root == null)
            return null;
        TreeNode leftTail = helper(root.left);
        TreeNode rightTail = helper(root.right);
        TreeNode rightTmp = root.right;
        if (leftTail != null) {
            root.right = root.left;
            root.left = null;
            leftTail.right = rightTmp;
        }
        return rightTail == null ? (leftTail == null ? root : leftTail) : rightTail;
    }
}
