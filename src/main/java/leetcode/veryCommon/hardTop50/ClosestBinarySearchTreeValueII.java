package leetcode.veryCommon.hardTop50;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClosestBinarySearchTreeValueII {

    //    Definition for a binary tree node.
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

    public void swap(int a, int b, List<Integer> l) {
        int tmp = l.get(a);
        l.set(a, l.get(b));
        l.set(b, tmp);
    }

    public int partition(int left, int right, int pivot, List<Integer> sortedTree, double target) {
        double pivotDiff = Math.abs(target - sortedTree.get(pivot));

        swap(pivot, right, sortedTree);

        int leftSetHead = left;

        for (int i = left; i < right; i++) {
            double currDiff = Math.abs(target - sortedTree.get(i));
            if (currDiff < pivotDiff) {
                swap(leftSetHead, i, sortedTree);
                leftSetHead++;
            }
        }
        swap(leftSetHead, right, sortedTree);
        return leftSetHead;
    }

    public void quickSelect(int left, int right, List<Integer> sortedTree, int kthSmallest, double target) {
        if (left >= right) return;

        Random r = new Random();
        int pivot = left + r.nextInt(right - left);

        int pivotPos = partition(left, right, pivot, sortedTree, target);

        if (pivotPos == kthSmallest) return;
        else if (pivotPos < kthSmallest)
            quickSelect(pivotPos + 1, right, sortedTree, kthSmallest, target);
        else
            quickSelect(left, pivot - 1, sortedTree, kthSmallest, target);
    }

    public void inorder(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> sortedTree = new ArrayList<>();
        inorder(root, sortedTree);
        quickSelect(0, sortedTree.size() - 1, sortedTree, k, target);
        return sortedTree.subList(0, k);
    }
}
