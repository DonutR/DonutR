package leetcode.veryCommon.tree;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        HashMap<Integer, Deque<Integer>> levelMap = new HashMap<>();
        recFun(root, levelMap, 0);
        List<List<Integer>> out = new ArrayList<>();
        for (Deque<Integer> dq : levelMap.values()) {
            out.add(new LinkedList<>(dq));
        }
        return out;
    }

    public void recFun(TreeNode root, HashMap<Integer, Deque<Integer>> levelMap, int level) {
        if (root != null) {
            if (levelMap.containsKey(level)) {
                if (level % 2 != 0)
                    levelMap.get(level).addFirst(root.val);
                else
                    levelMap.get(level).addLast(root.val);
            } else {
                Deque<Integer> tmp = new LinkedList<>();
                tmp.add(root.val);
                levelMap.put(level, tmp);
            }
            level++;
            recFun(root.left, levelMap, level);
            recFun(root.right, levelMap, level);
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        if (root == null)
            return out;
        q.add(root);
        q.add(null);
        int hight = 0;
        List<Integer> rowList = new LinkedList<>();
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                if (++hight % 2 == 0) Collections.reverse(rowList);
                out.add(new LinkedList<>(rowList));
                rowList.clear();
                if (!q.isEmpty()) q.add(null);
                continue;
            }
            rowList.add(curr.val);
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
        return out;
    }
}
