package leetcode.veryCommon.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//We know that a binary tree can be represented by an array (assume the root begins from the position with index 1 in the array).
// If the index of a node is i, the indices of its two children are 2*i and 2*i + 1.
// The idea is to use two arrays (start[] and end[]) to record the the indices of the leftmost node and rightmost node in each level, respectively.
// For each level of the tree, the width is end[level] - start[level] + 1. Then, we just need to find the maximum width.


public class MaximumWidthofBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    //DFS
    public int widthOfBinaryTreeDFS(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    public int dfs(TreeNode root, int level, int idx, List<Integer> start, List<Integer> end) {
        if (root == null) return 0;
        if (start.size() == level) {
            start.add(idx);
            end.add(idx);
        } else end.set(level, idx);
        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2 * idx, start, end);
        int right = dfs(root.right, level + 1, 2 * idx + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }

    //BFS
    public int widthOfBinaryTree(TreeNode root) {
        List<TreeNode> treeOneDimensional = new ArrayList<>();
        Queue<TreeOneDime> q = new LinkedList<>();
        int out = 0;
        if (root != null) {
            q.add(new TreeOneDime(root, 1));
            q.add(null);
            int level = 1;
            out++;
            while (!q.isEmpty()) {
                TreeOneDime curr = q.poll();
                if (curr == null) {
                    level++;
                    if (!q.isEmpty()) {
                        int min = q.stream().map(x -> x.oneDArrayIdx).min((a, b) -> a - b).get();
                        int max = q.stream().map(x -> x.oneDArrayIdx).max((a, b) -> a - b).get();
                        out = Math.max(out, max - min + 1);
                        q.add(null);
                    }
                } else {
                    if (curr.node.left != null) q.add(new TreeOneDime(curr.node.left, 2 * curr.oneDArrayIdx));
                    if (curr.node.right != null) q.add(new TreeOneDime(curr.node.right, 2 * curr.oneDArrayIdx + 1));
                }
            }
        }

        return out;
    }

    public class TreeOneDime {
        TreeNode node;
        int oneDArrayIdx;

        public TreeOneDime(TreeNode node, int idx) {
            this.node = node;
            this.oneDArrayIdx = idx;
        }
    }
}
