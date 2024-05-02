package leetcode.veryCommon.design;

public class DesignHashSet {
    class MyHashSet {
        public ListNode[] hashArr;

        class ListNode {
            int key;
            ListNode next;

            public ListNode(int key) {
                this.key = key;
            }
        }

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            hashArr = new ListNode[10000];
        }

        public void add(int key) {
            ListNode node = hashArr[key % 10000];
            if (node == null) {
                hashArr[key % 10000] = new ListNode(key);
                return;
            }
            while (node != null) {
                if (node.key == key)
                    return;
                else if (node.next == null) {
                    node.next = new ListNode(key);
                    return;
                }
                node = node.next;
            }
        }

        public void remove(int key) {
            ListNode prev = null;
            ListNode node = hashArr[key % 10000];
            while (node != null) {
                if (node.key == key) {
                    if (prev == null)
                        hashArr[key % 10000] = node.next;
                    else
                        prev.next = node.next;
                    return;
                }
                prev = node;
                node = node.next;
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            ListNode node = hashArr[key % 10000];
            while (node != null) {
                if (node.key == key)
                    return true;
                node=node.next;
            }
            return false;
        }
    }
}
