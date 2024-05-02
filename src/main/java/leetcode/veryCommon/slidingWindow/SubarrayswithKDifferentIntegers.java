package leetcode.veryCommon.slidingWindow;

import java.util.HashMap;

public class SubarrayswithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        int i = 0, out = 0;
        HashMap<Integer, Integer> window = new HashMap<>();
        for (int j = 0; j < A.length; j++) {
            window.put(A[j], window.getOrDefault(A[j], 0) + 1);
            if (window.size() == K)
                out += j - 1 + 1;
            else if (window.size() > K) {
                while (window.size() > K) {
                    window.put(A[i], window.get(A[i]) - 1);
                    if (window.get(A[i]) == 0)
                        window.remove(A[i]);
                    i++;
                }
                out++;
            }
        }
        return out;
    }
}
