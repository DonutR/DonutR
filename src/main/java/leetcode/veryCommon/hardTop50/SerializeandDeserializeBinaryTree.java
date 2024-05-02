package leetcode.veryCommon.hardTop50;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SerializeandDeserializeBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {
        String nullCnst = "NN";
        String separator = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        public void serializeHelper(TreeNode root, StringBuilder sb) {
            if (root == null)
                sb.append(nullCnst).append(separator);
            else {
                sb.append(root.val).append(separator);
                serializeHelper(root.left, sb);
                serializeHelper(root.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> dataQueue = new LinkedList<>(Arrays.asList(data.split(separator)));
            return deserializeHelper(dataQueue);
        }

        public TreeNode deserializeHelper(Deque<String> dataQueue) {
            String data = dataQueue.remove();
            TreeNode node = null;
            if (!data.equals(nullCnst)) {
                node = new TreeNode(Integer.parseInt(data));
                node.left = deserializeHelper(dataQueue);
                node.right = deserializeHelper(dataQueue);
            }
            return node;
        }
    }

    public class Codec2 {
        public String nullStr = "N";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.deleteCharAt(sb.length() - 1).toString();
        }

        public void serializeHelper(TreeNode root, StringBuilder sb) {
            if (root != null) {
                sb.append(root.val).append(",");
                serializeHelper(root.left, sb);
                serializeHelper(root.right, sb);
            } else {
                sb.append(nullStr).append(",");
            }
        }

        int idx = 0;

        public TreeNode deserialize(String data) {
            String[] dataArr = data.split(",");
            return deserializeHelper(dataArr);
        }

        public TreeNode deserializeHelper(String[] data) {
            if (data[idx].equals(nullStr)) return null;
            else {
                TreeNode newNode = new TreeNode(Integer.parseInt(data[idx]));
                ++idx;
                newNode.left = deserializeHelper(data);
                ++idx;
                newNode.right = deserializeHelper(data);
                return newNode;
            }
        }
    }
}
