package leetcode.veryCommon.twoPointer;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> out = new ArrayList<>();
        for (int i = 0, j = 0; i < A.length && j < B.length; ) {
            if ((B[j][0] >= A[i][0] && B[j][0] <= A[i][1]) || (B[j][1] >= A[i][0] && B[j][1] <= A[i][1])) {
                int[] tmp = {Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])};
                out.add(tmp);
                if (A[i][1] < B[j][1])
                    i++;
                else if (A[i][1] > B[j][1])
                    j++;
                else {
                    i++;
                    j++;
                }
            } else {
                if (A[i][1] < B[j][1])
                    i++;
                else if (A[i][1] > B[j][1])
                    j++;
            }
        }
        int[][] outArr = new int[out.size()][];
        out.toArray(outArr);
        return outArr;
    }
}
