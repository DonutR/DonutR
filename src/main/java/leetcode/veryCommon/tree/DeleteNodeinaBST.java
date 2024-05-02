package leetcode.veryCommon.tree;

public class DeleteNodeinaBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = new TreeNode(-1);
        parent.right = root;
        helper(root, key, parent);
        return parent.right;
    }

    public void helper(TreeNode root, int key, TreeNode parent) {
        if (root == null)
            return;
        else if (root.val > key)
            helper(root.left, key, root);
        else if (root.val < key)
            helper(root.right, key, root);
        else {
            TreeNode right = root.right;
            TreeNode newChild = (right != null ? right : root.left);
            if (right != null) {
                while (right.left != null)
                    right = right.left;
                right.left = root.left;
            }
            if (parent.right == root)
                parent.right = newChild;
            else parent.left = newChild;
        }
    }
}
