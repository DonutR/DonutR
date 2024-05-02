package leetcode.veryCommon.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class A24Game {
    boolean res = false;

    public boolean judgePoint24(int[] nums) {
        List<Double> arr = new ArrayList<>();
        for (int n : nums) arr.add((double) n);
        helper(arr);
        return res;
    }

    public void helper(List<Double> arr) {
        if (res) return;
        if (arr.size() == 1) {
            if (Math.abs(arr.get(0) - 24.0) < 0.001)
                res = true;
            else
                return;
        }
        for (int i = 0; i < arr.size(); i++)
            for (int j = 0; j < i; j++) {
                List<Double> nextList = new LinkedList<>();
                Double a = arr.get(i), b = arr.get(j);
                nextList.addAll(Arrays.asList(b + a, a + b, a - b, b - a, b * a, a * b, a / b, b / a));
                arr.remove(i);
                arr.remove(j);
                for (Double n : nextList) {
                    arr.add(n);
                    helper(arr);
                    arr.remove(arr.size() - 1);
                }
                arr.add(j, b);
                arr.add(i, a);
            }
    }
}
