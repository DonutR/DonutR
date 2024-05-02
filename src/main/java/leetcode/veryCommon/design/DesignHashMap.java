package leetcode.veryCommon.design;

public class DesignHashMap {
    class MyHashMap {
        public ListNode[] hashArray;

        class ListNode {
            int key, val;
            ListNode next;

            public ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            hashArray = new ListNode[10000];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            int hashIdx = key % 10000;
            ListNode node = hashArray[hashIdx];
            if (node == null)
                hashArray[hashIdx] = new ListNode(key, value);
            while (node != null) {
                if (node.key == key) {
                    node.val = value;
                    return;
                } else if (node.next == null) {
                    node.next = new ListNode(key, value);
                    return;
                }
                node = node.next;
            }
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            int hashIdx = key % 10000;
            ListNode node = hashArray[hashIdx];
            while (node != null) {
                if (node.key == key)
                    return node.val;
                node = node.next;
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            int hashIdx = key % 10000;
            ListNode prev = null;
            ListNode node = hashArray[hashIdx];
            while (node != null) {
                if (node.key == key) {
                    if (prev == null)
                        hashArray[hashIdx] = node.next;
                    else
                        prev.next = node.next;
                    return;
                }
                prev = node;
                node = node.next;
            }
        }
    }
}
