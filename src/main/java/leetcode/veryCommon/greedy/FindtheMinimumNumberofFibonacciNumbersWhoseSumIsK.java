package leetcode.veryCommon.greedy;

import java.util.ArrayList;
import java.util.Collections;

public class FindtheMinimumNumberofFibonacciNumbersWhoseSumIsK {
    public int findMinFibonacciNumbers(int k) {
        ArrayList<Integer> fib = getFibListItr(k);
        Collections.reverse(fib);
        for (int i = 0; i < fib.size(); i++) {
            int tmp = k, ct = 0;
            for (int j = i; j < fib.size(); j++) {
                if (tmp >= fib.get(j)) {
                    tmp -= fib.get(j);
                    ct++;
                    if (tmp == 0)
                        return ct;
                }

            }
        }
        return 0;
    }

    public void getFibList(int k, ArrayList<Integer> lst) {
        if (lst.get(lst.size() - 1) < k) {
            lst.add(lst.get(lst.size() - 1) + lst.get(lst.size() - 2));
            getFibList(k, lst);
        }
    }

    public ArrayList<Integer> getFibListItr(int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(1);
        result.add(1);
        while (result.get(result.size() - 1) < k) {
            result.add(result.get(result.size() - 1) + result.get(result.size() - 2));
        }
        return result;
    }
}
