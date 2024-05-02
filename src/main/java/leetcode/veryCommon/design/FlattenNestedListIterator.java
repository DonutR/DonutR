package leetcode.veryCommon.design;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     * <p>
     * // @return true if this NestedInteger holds a single integer, rather than a nested list.
     * public boolean isInteger();
     * <p>
     * // @return the single integer that this NestedInteger holds, if it holds a single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     * <p>
     * // @return the nested list that this NestedInteger holds, if it holds a nested list
     * // Return null if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     */
    public interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }

    public class NestedIterator1 implements Iterator<Integer> {

        Stack<NestedInteger> stk = new Stack<NestedInteger>();

        public NestedIterator1(List<NestedInteger> nestedList) {
            prepareStack(nestedList);
        }

        @Override
        public Integer next() {
            if (hasNext())
                return stk.pop().getInteger();
            else
                return null;
        }

        @Override
        public boolean hasNext() {
            while (!stk.isEmpty() && !stk.peek().isInteger()) {
                prepareStack(stk.pop().getList());
            }
            return stk.isEmpty();
        }

        public void prepareStack(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--)
                stk.push(nestedList.get(i));
        }
    }


    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> stack = new Stack<>();
        Integer nextInteger = null;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack.push(nestedList.iterator());
        }

        @Override
        public boolean hasNext() {
            if (nextInteger != null) return true;
            while (!stack.isEmpty()) {
                if (!stack.peek().hasNext()) stack.pop();
                else if (stack.peek().hasNext()) {
                    NestedInteger next = stack.peek().next();
                    if (next.isInteger()) {
                        nextInteger = next.getInteger();
                        return true;
                    } else
                        stack.push(next.getList().iterator());
                }
            }
            return false;
        }

        @Override
        public Integer next() {
            Integer out = null;
            if (nextInteger == null) {
                if (hasNext())
                    out = nextInteger;
            } else
                out = nextInteger;
            nextInteger = null;
            return out;
        }
    }
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
