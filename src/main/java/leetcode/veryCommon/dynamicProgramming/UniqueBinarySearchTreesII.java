package leetcode.veryCommon.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n < 1)
            return new ArrayList<>();
        return helper(1, n);
    }

    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        } else if (start == end) {
            list.add(new TreeNode(start));
            return list;
        } else {
            for (int i = start; i < end; i++) {
                List<TreeNode> leftList = helper(start, i - 1);
                List<TreeNode> rightList = helper(i + 1, end);
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        list.add(root);
                    }
                }
            }
        }
        return list;
    }
}
