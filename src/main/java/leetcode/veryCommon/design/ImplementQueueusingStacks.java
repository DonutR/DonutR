package leetcode.veryCommon.design;

import java.util.Stack;

public class ImplementQueueusingStacks {
    class MyQueue {
        Stack<Integer> stk;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stk = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            Stack<Integer> tmpStk = new Stack<>();
            stk.stream().forEach(a -> tmpStk.push(a));
            stk.clear();
            stk.push(x);
            tmpStk.stream().forEach(a -> stk.push(a));
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            return stk.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            return stk.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return !stk.isEmpty();
        }
    }
}
