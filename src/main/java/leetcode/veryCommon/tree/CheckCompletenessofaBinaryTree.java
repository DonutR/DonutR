package leetcode.veryCommon.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessofaBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr != null) {
                q.add(curr.left);
                q.add(curr.right);
            } else
                break;
        }
        return !(q.stream().filter(x -> x != null).count() > 0);
    }
}
