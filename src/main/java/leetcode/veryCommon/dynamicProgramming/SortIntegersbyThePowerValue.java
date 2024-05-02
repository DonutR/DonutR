package leetcode.veryCommon.dynamicProgramming;

import java.util.*;

public class SortIntegersbyThePowerValue {
    public int getKth(int lo, int hi, int k) {
        HashMap<Integer, Integer> tabDp = new HashMap<Integer, Integer>();
        Queue<int[]> kQ = new PriorityQueue<>((a, b) -> b[1] - a[1] == 0 ? b[0] - a[0] : b[1] - a[1]);
        for (int i = lo; i <= hi; i++) {
            int pow = getPower(i, tabDp);
            int[] el = {i, pow};
            kQ.add(el);
            if (kQ.size() > k)
                kQ.poll();
        }
        return kQ.poll()[0];
    }

    public int getPower(int val, HashMap<Integer, Integer> tabDp) {
        if (val == 1)
            return 0;
        ArrayList<Integer> path = new ArrayList<>();
        while (val != 1) {
            path.add(val);
            if (tabDp.containsKey(val)) {
                break;
            }
            if (val % 2 == 0)
                val = val / 2;
            else val = 3 * val + 1;
        }
        path.add(1);
        int pow = tabDp.getOrDefault(path.get(path.size() - 1), 0);
        for (int i = path.size() - 1; i >= 0; i--) {
            tabDp.put(path.get(i), pow);
            pow++;
        }
        return pow - 1;
    }
}
