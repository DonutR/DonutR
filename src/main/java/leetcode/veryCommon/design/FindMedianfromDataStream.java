package leetcode.veryCommon.design;

import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianfromDataStream {
    class MedianFinder {
        Queue<Integer> left;
        Queue<Integer> right;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            left = new PriorityQueue<Integer>((a, b) -> b - a);
            right = new PriorityQueue<Integer>((a, b) -> a - b);
        }

        public void addNum(int num) {
            if (left.isEmpty())
                left.add(num);
            else {
                if (num > left.peek())
                    right.add(num);
                else
                    left.add(num);
                if (Math.abs(left.size() - right.size()) > 1) {
                    if (right.size() > left.size())
                        left.add(right.poll());
                    else right.add(left.poll());
                }
            }
        }

        public double findMedian() {
            if (left.size() == 0)
                return 0;
            else if (left.size() == right.size())
                return (double) (left.peek() + right.peek()) / 2;
            else if (left.size() > right.size())
                return left.peek();
            else return right.peek();
        }
    }
}
