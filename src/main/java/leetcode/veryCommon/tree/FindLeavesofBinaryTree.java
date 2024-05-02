package leetcode.veryCommon.tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindLeavesofBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        Map<Integer, List<Integer>> heightMap = new HashMap<>();
        height(root, heightMap);
        return heightMap.entrySet().stream().map(es -> es.getValue()).collect(Collectors.toList());
    }

    public int height(TreeNode root, Map<Integer, List<Integer>> heightMap) {
        if (root != null) {
            int lHeight = height(root.left, heightMap);
            int rHeight = height(root.right, heightMap);
            int currHeight = Math.max(lHeight, rHeight);
            heightMap.computeIfAbsent(currHeight, x -> new ArrayList<>()).add(root.val);
            return currHeight + 1;
        }
        return 0;
    }
}
