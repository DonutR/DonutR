package leetcode.veryCommon.tree;

import java.util.HashMap;

public class PathSumIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int pathSum(TreeNode root, int sum) {
        HashMap<Long, Integer> preSum = new HashMap();
        preSum.put(0l, 1);
        helper(root, 0, sum, preSum);
        return count;
    }

    int count = 0;

    public void helper(TreeNode root, long currSum, int target, HashMap<Long, Integer> preSum) {
        if (root == null) {
            return;
        }

        currSum += root.val;

        if (preSum.containsKey(currSum - target)) {
            count += preSum.get(currSum - target);
        }

        if (!preSum.containsKey(currSum)) {
            preSum.put(currSum, 1);
        } else {
            preSum.put(currSum, preSum.get(currSum) + 1);
        }

        helper(root.left, currSum, target, preSum);
        helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
    }
}
