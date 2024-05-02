package leetcode.veryCommon.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    HashMap<Integer, Node> alreadyCreatedNewNodes;

    public Node cloneGraph(Node node) {
        alreadyCreatedNewNodes = new HashMap<>();
        return dfs(node);
    }

    public Node dfs(Node node) {
        if (node == null)
            return null;
        else if (!alreadyCreatedNewNodes.containsKey(node.val)) {
            ArrayList<Node> newNodeList = new ArrayList<Node>();
            Node newNode = new Node(node.val, newNodeList);
            alreadyCreatedNewNodes.put(node.val, newNode);
            for (Node n : node.neighbors) {
                Node newChild = dfs(n);
                if (newChild != null)
                    newNodeList.add(newChild);
            }
            return newNode;
        } else return alreadyCreatedNewNodes.get(node.val);
    }
}
