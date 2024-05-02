package leetcode.veryCommon.tree;

import java.util.Stack;

public class ValidateBinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    boolean result = true;

    //Recursion 1 Bottom Up
    public boolean isValidBSTRecursion1(TreeNode root) {
        result = true;
        helper1(root);
        return result;
    }

    public int[] helper1(TreeNode root) {
        if (!result) return null;
        else if (root != null) {
            int[] left = helper1(root.left);
            int[] right = helper1(root.right);
            if (left != null && left[1] >= root.val || right != null && right[0] <= root.val)
                result = false;
            return new int[]{left != null ? left[0] : root.val, right != null ? right[1] : root.val};
        } else return null;
    }

    //Recursion 2 Top Down
    public boolean isValidBSTRecursion2(TreeNode root) {
        return helper2(root, null, null);
    }

    public boolean helper2(TreeNode root, Integer min, Integer max) {
        if (root != null) {
            if (min != null && min >= root.val) return false;
            if (max != null && max <= root.val) return false;
            boolean left = false, right = false;
            left = helper2(root.left, min, root.val);
            if (left)
                right = helper2(root.right, root.val, max);
            return left && right;
        } else return true;
    }

    Integer inOrderHead = null;

    //Recursion 3 InOrder --- Beats 100% solutions
    public boolean isValidBSTRecursion3(TreeNode root) {
        inOrderHead = null;
        return helper3(root);
    }

    public boolean helper3(TreeNode root) {
        if (root != null) {
            if (!helper3(root.left)) return false;
            if (inOrderHead != null && root.val <= inOrderHead)
                return false;
            inOrderHead = root.val;
            if (!helper3(root.right)) return false;
            return true;
        } else return true;
    }

    //       2
    //      / \
    //     1   3
    //    / \   \
    //   0   2   4
    //  /
    //-1
    //Iterative Inorder Traversal using Stack
    public boolean isValidBSTStack1(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (inOrderHead != null && root.val <= inOrderHead) return false;
            inOrderHead = root.val;
            root = root.right;
        }
        return true;
    }

    public boolean isValidBSTStack2(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            if (!stack.isEmpty()) {
                curNode = stack.pop();
                if (inOrderHead != null && curNode.val <= inOrderHead) return false;
                inOrderHead = curNode.val;
                stack.push(curNode.right);
            }
        }
        return true;
    }
}
