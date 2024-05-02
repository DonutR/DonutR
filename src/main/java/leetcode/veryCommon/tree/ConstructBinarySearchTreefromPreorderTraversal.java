package leetcode.veryCommon.tree;

public class ConstructBinarySearchTreefromPreorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Each child is give a Max value, if the next element in Preorder greater than max value return.
    int idx = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstHelper(preorder, Integer.MAX_VALUE);
    }

    public TreeNode bstHelper(int[] preorder, int bound) {
        if (idx < preorder.length && preorder[idx] < bound) {
            TreeNode node = new TreeNode(preorder[idx++]);
            node.left = bstHelper(preorder, node.val);
            node.right = bstHelper(preorder, bound);
            return node;
        }
        return null;
    }
}
