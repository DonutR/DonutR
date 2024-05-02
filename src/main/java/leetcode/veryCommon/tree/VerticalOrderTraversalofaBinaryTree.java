package leetcode.veryCommon.tree;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversalofaBinaryTree {

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

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, List<Integer[]>> vOrder = new HashMap<>();
        helper(root, 0, 0, vOrder);
        return vOrder.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(a -> a.getValue().stream().sorted((c, d) -> {
                    if (c[1] - d[1] == 0)
                        return c[0] - d[0];
                    else return c[1] - d[1];
                }).map(f -> f[0]).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public void helper(TreeNode root, int column, int row, HashMap<Integer, List<Integer[]>> vOrder) {
        if (root != null) {
            vOrder.computeIfAbsent(column, x -> new ArrayList<>()).add(new Integer[]{root.val, row});
            helper(root.left, column - 1, row + 1, vOrder);
            helper(root.right, column + 1, row + 1, vOrder);
        }
    }
}
