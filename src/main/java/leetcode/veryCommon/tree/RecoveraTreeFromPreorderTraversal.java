package leetcode.veryCommon.tree;

import java.util.LinkedList;
import java.util.Queue;

public class RecoveraTreeFromPreorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        recoverFromPreorder("1-2--3--4-5--6--7");
    }


    public static TreeNode recoverFromPreorder(String S) {
        Queue<Integer[]> preQ = new LinkedList<>();
        for (int i = 0; i < S.length(); ) {
            int ct = 0;
            Integer[] tmp = new Integer[2];
            while (i < S.length() && S.charAt(i) == '-') {
                ct++;
                i++;
            }
            tmp[0] = ct;
            StringBuilder sb = new StringBuilder();
            while (i < S.length() && S.charAt(i) != '-') {
                sb.append(S.charAt(i));
                i++;
            }
            tmp[1] = Integer.parseInt(sb.toString());
            preQ.add(tmp);
        }

        return recFun(preQ);
    }

    public static TreeNode recFun(Queue<Integer[]> preQ) {
        if (!preQ.isEmpty()) {
            Integer[] cur = preQ.poll();
            TreeNode node = new TreeNode(cur[1]);
            if (!preQ.isEmpty() && cur[0] < preQ.peek()[0]) {
                node.left = recFun(preQ);
                if (!preQ.isEmpty() && cur[0] < preQ.peek()[0]) {
                    node.right = recFun(preQ);
                }
            }
            return node;
        }
        return null;
    }
}