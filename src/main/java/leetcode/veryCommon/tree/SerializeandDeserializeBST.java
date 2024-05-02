package leetcode.veryCommon.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SerializeandDeserializeBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {
        public String serialize(TreeNode root) {
            List<String> sb = new LinkedList<>();
            serializeHelper(root, sb);
            return sb.stream().collect(Collectors.joining(","));
        }

        public void serializeHelper(TreeNode root, List<String> sb) {
            if (root == null) {
                sb.add("N");
                return;
            }
            sb.add(root.val + "");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }

        public TreeNode deserialize(String data) {
            System.out.println(data);
            List<String> tree = Arrays.stream(data.split(",")).collect(Collectors.toList());
            return deserializeHelper(tree);
        }

        public TreeNode deserializeHelper(List<String> tree) {
            String currEl = tree.remove(0);
            if (currEl.equals("N"))
                return null;
            TreeNode node = new TreeNode(Integer.parseInt(currEl));
            node.left = deserializeHelper(tree);
            node.right = deserializeHelper(tree);
            return node;
        }
    }
}
