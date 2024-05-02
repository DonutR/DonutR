package leetcode.veryCommon.tree;

public class TrimaBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        TreeNode ret = null;
        if (root == null)
            return null;
        else if (root.val >= low && root.val <= high) {
            ret = root;
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        } else if (root.val > high) ret = trimBST(root.left, low, Math.min(high, root.val));
        else if (root.val < low) ret = trimBST(root.right, Math.max(low, root.val), high);
        return ret;
    }
}
