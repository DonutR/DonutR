package leetcode.veryCommon.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {
    public class MinStack1 {
        Deque<Integer> ds;
        PriorityQueue<Integer> pq;


        /**
         * initialize your data structure here.
         */
        public MinStack1() {
            ds = new LinkedList<>();
            pq = new PriorityQueue<Integer>();
        }

        public void push(int x) {
            pq.offer(x);
            ds.addFirst(x);
        }

        public void pop() {
            pq.remove(ds.peekFirst());
            ds.removeFirst();
        }

        public int top() {
            return ds.peekFirst();
        }

        public int getMin() {
            return pq.peek();
        }
    }

    public class MinStack2 {
        Stack<Integer> stack;
        int min = Integer.MAX_VALUE;

        public MinStack2() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                stack.push(x);
                min = x;
            } else stack.push(x);
        }

        public void pop() {
            if (stack.peek() == min) {
                stack.pop();
                min = stack.pop();
            } else stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}