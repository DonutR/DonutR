package leetcode.veryCommon.list;

import java.util.HashMap;

public class CopyListwithRandomPointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    ;

    public Node copyRandomList2(Node head) {
        HashMap<Node, Integer> nodeIdMap = new HashMap<>();
        int id = 0;
        Node tmpHead = head;
        while (tmpHead != null) {
            nodeIdMap.put(tmpHead, id);
            id++;
            tmpHead = tmpHead.next;
        }
        Node[] outArr = new Node[id];
        tmpHead = head;
        id = 0;
        while (tmpHead != null) {
            outArr[id] = new Node(tmpHead.val, null, null);
            id++;
            tmpHead = tmpHead.next;
        }
        tmpHead = head;
        id = 0;
        while (tmpHead != null) {
            Node newNode = outArr[id];
            if (tmpHead.random != null) newNode.random = outArr[nodeIdMap.get(tmpHead.random)];
            id++;
            tmpHead = tmpHead.next;
        }
        for (int i = 0; i < outArr.length - 1; i++) {
            outArr[i].next = outArr[i + 1];
        }
        return outArr.length >= 1 ? outArr[0] : null;
    }

    HashMap<Node, Node> nodeIdMap = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Node tmpNode = new Node(head.val, null, null);
        nodeIdMap.put(head, tmpNode);
        Node next = copyRandomList(head.next);
        tmpNode.next = next;
        tmpNode.random = nodeIdMap.getOrDefault(head.random, null);
        return tmpNode;
    }

}
