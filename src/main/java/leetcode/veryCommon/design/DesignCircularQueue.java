package leetcode.veryCommon.design;

public class DesignCircularQueue {
    class MyCircularQueue {
        int currCt = 0, maxCt = 0;
        Node head, tail;

        class Node {
            int value;
            Node prev;
            Node next;

            public Node(int value) {
                this.value = value;
            }
        }

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            head = null;
            tail = null;
            currCt = 0;
            maxCt = k;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {
            if (currCt == maxCt)
                return false;
            Node newNode = new Node(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            currCt++;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {
            if (currCt == 0)
                return false;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head.next.prev = null;
                head = head.next;
            }
            currCt--;
            return true;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {
            if (isEmpty())
                return -1;
            else return head.value;
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {
            if (isEmpty())
                return -1;
            else return tail.value;
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return currCt == 0;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            return currCt == maxCt;
        }
    }
}
