package leetcode.veryCommon.tree;

public class KthSmallestElementinaBST {

    public int ct = 0;

    public int kthSmallest1(TreeNode root, int k) {
        ct = 0;
        return helper(root, k);
    }

    public int helper(TreeNode root, int k) {
        if (root != null) {
            int out = helper(root.left, k);
            if (ct == k) return out;
            ct++;
            if (ct == k)
                return root.val;
            return helper(root.right, k);
        }
        return -1;
    }

    int out = 0;

    public int kthSmallest(TreeNode root, int k) {
        out = 0;
        helper2(root, k, 0);
        return out;
    }

    public int helper2(TreeNode root, int k, int ct) {
        if (ct < k && root != null) {
            ct = helper2(root.left, k, ct);
            if (++ct == k) {
                out = root.val;
                return k;
            }
            return helper2(root.right, k, ct);
        }
        return ct;
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
