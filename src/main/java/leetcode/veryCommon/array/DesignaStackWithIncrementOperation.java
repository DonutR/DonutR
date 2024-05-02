package leetcode.veryCommon.array;

public class DesignaStackWithIncrementOperation {
    class CustomStack {
        int[] data;
        int idx = -1;
        int maxSize = 0;

        public CustomStack(int maxSize) {
            this.maxSize = maxSize;
            data = new int[maxSize];
        }

        public void push(int x) {
            if (idx + 1 < maxSize)
                data[++idx] = x;
        }

        public int pop() {
            return idx >= 0 ? data[idx--] : -1;
        }

        public void increment(int k, int val) {
            for (int i = 0; i < k && i <= idx && idx >= 0; i++) {
                data[i] = data[i] + val;
            }
        }
    }
}
