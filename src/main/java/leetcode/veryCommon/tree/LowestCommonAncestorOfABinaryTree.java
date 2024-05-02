package leetcode.veryCommon.tree;

public class LowestCommonAncestorOfABinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recFun(root, p, q);
        return ans;
    }

    public boolean recFun(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null) {
            boolean a = recFun(root.left, p, q);
            boolean b = recFun(root.right, p, q);
            boolean c = root == p || root == q;
            if (ans == null && (a && b || a && c || b && c))
                ans = root;
            if (a || b || root == p || root == q)
                return true;
        }
        return false;
    }
}
