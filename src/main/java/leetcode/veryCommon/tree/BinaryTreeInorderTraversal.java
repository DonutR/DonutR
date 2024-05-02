package leetcode.veryCommon.tree;

import java.util.*;
import java.util.stream.Collectors;




public class BinaryTreeInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    //[[1,2],[2,3],[2,5]]
    //[[2,1],[1,4]]
    //[[1,4],[2,1]]
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        List<Integer> out = new ArrayList<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            out.add(root.val);
            root = root.right;
        }
        return out;
    }
}
