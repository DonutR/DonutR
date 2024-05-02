package leetcode.veryCommon.tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        HashMap<Integer, List<Integer>> levelMap = new HashMap<>();
        recFun(root, levelMap, 0);
        return new ArrayList<>(levelMap.values());
    }

    public void recFun(TreeNode root, HashMap<Integer, List<Integer>> levelMap, int level) {
        if (root != null) {
            if (levelMap.containsKey(level)) {
                levelMap.get(level).add(root.val);
            } else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(root.val);
                levelMap.put(level, tmp);
            }
            level++;
            recFun(root.left, levelMap, level);
            recFun(root.right, levelMap, level);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> out = new LinkedList<>();
        if (root == null)
            return out;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                if (!q.isEmpty()) {
                    out.add(new LinkedList<Integer>());
                    q.add(null);
                }
            } else {
                out.get(out.size() - 1).add(curr.val);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return out;
    }
}

