package leetcode.veryCommon.design;

public class DesignLinkedList {
    class MyLinkedList {
        class Node {
            int val;
            Node prev;
            Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        Node head, tail;

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {
            head = null;
            tail = null;
        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public int get(int index) {
            Node node = head;
            int i = 0;
            while (i < index) {
                node = node.next;
                if (node == null)
                    return -1;
                i++;
            }
            return node.val;
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            Node newNode = new Node(val);
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public void addAtIndex(int index, int val) {
            Node node = head;
            Node newNode = new Node(val);
            int i = 0;
            while (i < index) {
                node = node.next;
                if (node == null) break;
                i++;
            }
            System.out.println(node.val);
            if (node == null) return;
            else if (head == node)
                addAtHead(val);
            else if (tail == node)
                addAtTail(val);
            else {
                newNode.next = node;
                newNode.prev = node.prev;
            }
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            Node node = head;
            int i = 0;
            while (i < index) {
                node = node.next;
                if (node == null)
                    return;
                i++;
            }
            if (head == node)
                head = node.next;
            if (tail == node)
                tail = node.prev;
            if (node.next != null)
                node.next.prev = node.prev;
            if (node.prev != null)
                node.prev.next = node.next;
        }
    }
}
