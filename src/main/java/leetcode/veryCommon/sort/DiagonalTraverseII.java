package leetcode.veryCommon.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiagonalTraverseII {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer[]> tmpList = new ArrayList<>();
        int i = 0;
        for (List<Integer> lsEl : nums) {
            int j = 0;
            for (Integer el : lsEl) {
                Integer[] tmp = {i, j, el};
                tmpList.add(tmp);
                j++;
            }
            i++;
        }
        //tmpList.stream().forEach(a -> System.out.println(a[0] + " " + a[1] + " " + " " + a[2]));


        tmpList.stream().sorted((a, b) ->
        {
            System.out.println(a[0] + " " + a[1] + " " + " " + a[2]);
            int diff = a[0] + a[1] - b[0] + b[1];
            if (diff == 0)
                return b[1] - a[1];
            else return diff;
        }).collect(Collectors.toList());


        return tmpList.stream().sorted((a, b) ->
        {
            int diff = a[0] + a[1] - b[0] + b[1];
            if (diff == 0)
                return b[1] - a[1];
            else return diff;
        }).map(a -> a[2]).mapToInt(a -> a).toArray();
    }
}
