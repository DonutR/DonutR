package leetcode.veryCommon.hardTop50;

import java.util.*;

public class SerializeandDeserializeNArrayTree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    public class Codec {
        String nullCnst = "NN";
        String separator = ",";

        // Encodes a tree to a single string.
        public String serialize(Node root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        public void serializeHelper(Node root, StringBuilder sb) {
            if (root == null)
                sb.append(nullCnst).append(separator);
            else {
                sb.append(root.val).append(separator);
                for (Node n : root.children)
                    serializeHelper(n, sb);
                sb.append(nullCnst).append(separator);
            }
        }

        int idx = 0;

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            String[] dataArr = data.split(",");
            idx = 0;
            return deserializeHelper(dataArr);
        }

        public Node deserializeHelper(String[] data) {
            if (!data[idx].equals(nullCnst)) {
                Node node = new Node(Integer.parseInt(data[idx]));
                List<Node> children = new ArrayList<>();
                node.children = children;
                ++idx;
                while (!data[idx].equals(nullCnst)) {
                    children.add(deserializeHelper(data));
                    ++idx;
                }
                return node;
            } else return null;
        }
    }
}
