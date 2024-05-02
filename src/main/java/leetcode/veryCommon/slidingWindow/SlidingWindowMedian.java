package leetcode.veryCommon.slidingWindow;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Supplier;

public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> right = new TreeSet<>(comparator);

        Supplier<Double> median = (k % 2 == 0) ?
                () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
                () -> (double) nums[right.first()];

        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < k; i++)
            left.add(i);

        balance(left, right);
        result[0] = median.get();

        for (int i = k, r = 1; i < nums.length; i++, r++) {
            // remove tail of window from either left or right
            if (!left.remove(i - k)) right.remove(i - k);

            right.add(i);
            left.add(right.pollFirst());

            balance(left, right);

            result[r] = median.get();
        }
        return result;
    }

    public void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
        while (left.size() > right.size())
            right.add(left.pollFirst());
    }
}