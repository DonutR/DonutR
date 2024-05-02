package random;

public class BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode searchEqual(TreeNode root, int t) {
        TreeNode ret = null;
        if (root != null) {
            if (t < root.val)
                ret = searchEqual(root.left, t);
            else if (t > root.val)
                ret = searchEqual(root.right, t);
            else if (t == root.val)
                ret = root;
        }
        return ret;
    }

    public static TreeNode searchLessThanOrEqual(TreeNode root, int t) {
        TreeNode ret = null;
        if (root != null) {
            if (t < root.val)
                ret = searchEqual(root.left, t);
            else if (t > root.val) {
                ret = searchEqual(root.right, t);
                if (ret == null) ret = root;
            } else if (t == root.val)
                ret = root;
        }
        return ret;
    }

    public static TreeNode searchGreaterThanOrEqual(TreeNode root, int t) {
        TreeNode ret = null;
        if (root != null) {
            if (t < root.val) {
                ret = searchEqual(root.left, t);
                if (ret == null) ret = root;
            } else if (t > root.val)
                ret = searchEqual(root.right, t);
            else if (t == root.val)
                ret = root;
        }
        return ret;
    }
}
