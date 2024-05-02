package leetcode.veryCommon.tree;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int rob(TreeNode root) {
        return robSub(root, new HashMap<>());
    }

    //Memorization
    private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int val = 0;

        if (root.left != null) {
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }

        if (root.right != null) {
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }

        val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);

        return val;
    }

    //Recursion
    public int rob1(TreeNode root) {
        int[] answer = helper(root);
        return Math.max(answer[0], answer[1]);
    }

    public int[] helper(TreeNode node) {
        // return [rob this node, not rob this node]
        if (node == null) {
            return new int[]{0, 0};
        }
        int left[] = helper(node.left);
        int right[] = helper(node.right);
        // if we rob this node, we cannot rob its children
        int rob = node.val + left[1] + right[1];
        // else, we free to choose rob its children or not
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{rob, notRob};
    }

    HashMap<TreeNode, Integer> robResult = new HashMap<>();
    HashMap<TreeNode, Integer> notRobResult = new HashMap<>();

    //Memorisation
    public int rob2(TreeNode root) {
        return helper(root, false);
    }

    public int helper(TreeNode node, boolean isParentRobed) {
        if (node == null)
            return 0;
        if (isParentRobed) {
            if (notRobResult.containsKey(node))
                return notRobResult.get(node);
            int result = helper(node.left, false) + helper(node.right, false);
            notRobResult.put(node, result);
            return result;
        } else {
            if (robResult.containsKey(node))
                return robResult.get(node);
            int rob = node.val + helper(node.left, true) + helper(node.right, true);
            int notRob = helper(node.left, false) + helper(node.right, false);
            int result = Math.max(rob, notRob);
            robResult.put(node, result);
            return result;
        }
    }
}
