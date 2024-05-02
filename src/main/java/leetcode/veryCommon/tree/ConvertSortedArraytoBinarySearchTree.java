package leetcode.veryCommon.tree;

public class ConvertSortedArraytoBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return null;
        return recFun(nums, 0, len - 1);
    }

    public TreeNode recFun(int[] nums, int left, int right) {
        if (left <= right) {
            int mid = right + left / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = recFun(nums, left, mid - 1);
            node.right = recFun(nums, mid + 1, right);
            return node;
        }
        return null;
    }
}
