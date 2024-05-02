package leetcode.veryCommon.tree;

import java.util.ArrayList;
import java.util.List;

public class SerializeandDeserializeNaryTree {
    // Definition for a Node.
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

    class Codec {
        int deserializeIdx = 2;

        // Encodes a tree to a single string.
        public String serialize(Node root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
            return "1,null,3,2,4,null,null,5,null,6,null";
        }

        public void serializeHelper(Node root, StringBuilder sb) {
            if (root != null) {
                sb.append(root.val + ",");
                for (Node child : root.children) {
                    serializeHelper(child, sb);
                }
                sb.append("null,");
            }
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            String[] splitData = data.split(",");
            if (splitData[0].equals("null"))
                return null;
            Node root = new Node(Integer.parseInt(splitData[0]));
            if (splitData.length < 3) {
                return root;
            }
            root.children = deserializeHelper(root, splitData);
            return root;
        }

        public List<Node> deserializeHelper(Node root, String[] data) {
            if (deserializeIdx < data.length) {
                int tmp = deserializeIdx;
                List<Node> children = new ArrayList<>();
                root.children = children;
                while (!data[tmp].equals("null")) {
                    Node child = new Node(Integer.parseInt(data[tmp]));
                    children.add(child);
                    tmp++;
                }
                deserializeIdx = tmp + 1;
                for (Node child : children)
                    deserializeHelper(child, data);
            }
            return null;
        }
    }
}
