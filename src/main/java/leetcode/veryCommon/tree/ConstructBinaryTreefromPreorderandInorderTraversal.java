package leetcode.veryCommon.tree;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int preIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIdx = 0;
        return helper(0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx++]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(inStart, inIndex - 1, preorder, inorder);
        root.right = helper(inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}
