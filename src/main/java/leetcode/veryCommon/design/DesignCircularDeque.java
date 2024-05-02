package leetcode.veryCommon.design;

public class DesignCircularDeque {
    class MyCircularDeque {
        class ListNode {
            int value;
            ListNode prev;
            ListNode next;

            public ListNode(int value) {
                this.value = value;
            }
        }

        int maxSize, currCt;
        ListNode head, tail;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            this.maxSize = k;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if (isFull())
                return false;
            currCt++;
            ListNode newNode = new ListNode(value);
            newNode.next = head;
            if (head != null)
                head.prev = newNode;
            head = newNode;
            if (tail == null)
                tail = newNode;
            return true;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if (isFull())
                return false;
            currCt++;
            ListNode newNode = new ListNode(value);
            newNode.prev = tail;
            if (tail != null)
                tail.next = newNode;
            tail = newNode;
            if (head == null)
                head = newNode;
            return true;
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (isEmpty())
                return false;
            currCt--;
            head = head.next;
            if (currCt > 0)
                head.prev = null;
            if (currCt == 0)
                tail = null;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (isEmpty())
                return false;
            currCt--;
            tail = tail.prev;
            if (currCt > 0)
                tail.next = null;
            if (currCt == 0)
                head = null;
            return true;
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (isEmpty())
                return -1;
            return head.value;
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (isEmpty())
                return -1;
            return tail.value;
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            if (currCt == 0)
                return true;
            return false;
        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            if (currCt == maxSize)
                return true;
            return false;
        }
    }

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
}
