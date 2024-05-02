package leetcode.veryCommon.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathSumII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    //DFS+Backtracking
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> out = new ArrayList<>();
        helper(root, targetSum, out, new ArrayList<>(), 0);
        return out;
    }

    public void helper(TreeNode root, int targetSum, List<List<Integer>> out, ArrayList<Integer> currentPath, int sum) {
        if (root != null) {
            sum += root.val;
            currentPath.add(root.val);
            if (root.left == null && root.right == null && sum == targetSum)
                out.add(new ArrayList<>(currentPath));
            helper(root.left, targetSum, out, currentPath, sum);
            helper(root.right, targetSum, out, currentPath, sum);
            currentPath.remove(currentPath.size() - 1);
        }
    }

    //BFS using path as custom Class
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> out = new LinkedList<>();
        Queue<Node> q = new LinkedList<>();
        List<Integer> interOut = new LinkedList<Integer>();
        if (root != null) {
            interOut.add(root.val);
            q.add(new Node(root, root.val, interOut));
        }
        while (!q.isEmpty()) {
            Node currNode = q.poll();
            if (currNode.root.left == null && currNode.root.right == null && currNode.val == sum)
                out.add(currNode.interOut);
            if (currNode.root.left != null) {
                interOut = new LinkedList<Integer>();
                interOut.addAll(currNode.interOut);
                interOut.add(currNode.root.left.val);
                q.add(new Node(currNode.root.left, currNode.val + currNode.root.left.val, interOut));
            }
            if (currNode.root.right != null) {
                interOut = new LinkedList<Integer>();
                interOut.addAll(currNode.interOut);
                interOut.add(currNode.root.right.val);
                q.add(new Node(currNode.root.right, currNode.val + currNode.root.right.val, interOut));
            }
        }
        return out;
    }

    public class Node {
        public TreeNode root;
        public int val;
        public List<Integer> interOut;

        public Node(TreeNode root, int val, List<Integer> interOut) {
            this.root = root;
            this.val = val;
            this.interOut = interOut;
        }
    }
}
