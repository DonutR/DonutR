package leetcode.veryCommon.tree;


import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeVerticalOrderTraversal {
    int leftNessCounter = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        leftNessCounter = 0;
        Map<Integer, List<List<Integer>>> out = new HashMap();
        helper(root, out, 0, 0);
        return out.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(c -> c.getValue())
                .map(pq -> pq.stream()
                        .sorted((a, b) -> a.get(0) - b.get(0) == 0 ? a.get(2) - b.get(2) : a.get(0) - b.get(0)).map(d -> d.get(1))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public void helper(TreeNode root, Map<Integer, List<List<Integer>>> out, int column, int row) {
        if (root != null) {
            leftNessCounter += 1;
            out.computeIfAbsent(column, x -> new ArrayList<>())
                    .add(Arrays.asList(row, root.val, leftNessCounter));
            helper(root.left, out, column - 1, row + 1);
            helper(root.right, out, column + 1, row + 1);
        }
    }

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}
