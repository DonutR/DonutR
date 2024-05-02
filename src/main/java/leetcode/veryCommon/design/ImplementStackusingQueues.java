package leetcode.veryCommon.design;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueues {
    class MyStack {
        Queue<Integer> q;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            q = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            Queue<Integer> tmpQ = new LinkedList<>();
            q.stream().forEach(a -> tmpQ.add(a));
            q.clear();
            q.add(x);
            tmpQ.stream().forEach(a -> q.add(a));
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return q.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return q.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q.isEmpty();
        }
    }
}
