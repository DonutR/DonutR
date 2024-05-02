package leetcode.veryCommon.tree;


import java.util.*;

public class AllNodesDistanceKinBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<Integer> out = new ArrayList<>();
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        out = new ArrayList<>();
        generateParentMap(root, null);
        helperBottomUp(target, k);
        return out;
    }

    public void generateParentMap(TreeNode root, TreeNode parent) {
        if (root != null) {
            parentMap.put(root, parent);
            generateParentMap(root.left, root);
            generateParentMap(root.right, root);
        }
    }

    public void helperTopDown(TreeNode root, int k, int childDist) {
        if (root != null) {
            if (childDist == k) {
                out.add(root.val);
                return;
            }
            helperTopDown(root.left, k, childDist + 1);
            helperTopDown(root.right, k, childDist + 1);
        }
    }

    public void helperBottomUp(TreeNode target, int k) {
        TreeNode curr = target, prev = null;
        int parentLevelCounter = 1;
        while (curr != null) {
            TreeNode l = curr.left, r = curr.right;
            int newK = k - parentLevelCounter;
            if (newK + 1 == 0) out.add(curr.val);
            if (prev != l)
                helperTopDown(l, newK, 0);
            if (prev != r)
                helperTopDown(r, newK, 0);
            prev = curr;
            curr = parentMap.get(curr);
            parentLevelCounter++;
        }
    }
}
