package leetcode.veryCommon.tree;

import java.util.*;
import java.util.stream.Collectors;

public class SymmetricTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    public boolean isSymmetric1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        HashMap<Integer, List<Integer>> treeLevelOrder = new HashMap<>();
        helper(root, 0, treeLevelOrder);
        for (List<Integer> level : treeLevelOrder.values()) {
            List<Integer> revLevel = new LinkedList<>(level);
            Collections.reverse(revLevel);
            if (!level.equals(revLevel))
                return false;
        }
        return true;
    }

    public void helper(TreeNode root, int hight, HashMap<Integer, List<Integer>> treeLevelOrder) {
        if (root != null) {
            treeLevelOrder.computeIfAbsent(hight, x -> new LinkedList<>()).add(root.val);
            helper(root.left, hight + 1, treeLevelOrder);
            helper(root.right, hight + 1, treeLevelOrder);
        } else
            treeLevelOrder.computeIfAbsent(hight, x -> new LinkedList<>()).add(null);
    }

    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> level = new LinkedList<>();
        if (root != null) q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            if (t1 != null) {
                if (t1.left != null) q.add(t1.left);
                if (t1.right != null) q.add(t1.right);
                level.add(t1.left != null ? t1.left.val : null);
                level.add(t1.right != null ? t1.right.val : null);
            } else if (t1 == null && !q.isEmpty()) {
                List<Integer> revLevel = new LinkedList<>(level);
                Collections.reverse(revLevel);
                if (!level.equals(revLevel))
                    return false;
                level = new LinkedList<>();
                q.add(null);
            }
        }
        return true;
    }
}
