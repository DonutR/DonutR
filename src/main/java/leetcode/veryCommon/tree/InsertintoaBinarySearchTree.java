package leetcode.veryCommon.tree;

public class InsertintoaBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        recHelper(root, val);
        return root;
    }

    public void recHelper(TreeNode root, int val) {
        if (root != null) {
            if (root.val > val) {
                if (root.left == null)
                    root.left = new TreeNode(val);
                else
                    recHelper(root.left, val);
            } else {
                if (root.right == null)
                    root.right = new TreeNode(val);

                else
                    recHelper(root.right, val);
            }
        }
    }
}
