package leetcode.veryCommon.random;

import java.util.*;
import java.util.stream.Collectors;

public class PrisonCellsAfterNDays {
    public static void main(String[] args) {
        int[] cells = {0, 1, 0, 1, 1, 0, 0, 1};
        int N = 10000000;
        Arrays.stream(prisonAfterNDays(cells, N)).forEach(System.out::print);
    }

    public static int[] prisonAfterNDays(int[] cells, int N) {


        HashMap<String, Integer> memo = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(cells).forEach(a -> sb.append(a));
        memo.put(sb.toString(), memo.size());

        int[] cellsTmp = new int[cells.length];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < cells.length - 1; j++) {
                if (cells[j - 1] == cells[j + 1])
                    cellsTmp[j] = 1;
                else cellsTmp[j] = 0;
            }

            if (i == 0) {
                cellsTmp[0] = 0;
                cellsTmp[cellsTmp.length - 1] = 0;
            }
            for (int k = 0; k < cells.length; k++)
                cells[k] = cellsTmp[k];

            StringBuilder sb1 = new StringBuilder();
            Arrays.stream(cells).forEach(a -> sb1.append(a));
            if (i + 1 < N && memo.containsKey(sb1.toString())) {
                int remDays = N % memo.size();
                Map.Entry<String, Integer> es = memo.entrySet().
                        stream().
                        filter(a -> a.getValue() == remDays).
                        collect(Collectors.toList()).
                        get(0);
                int[] out = new int[cells.length];
                int ct = 0;
                for (char c : es.getKey().toCharArray()) {
                    out[ct] = c - 48;
                    ct++;
                }
                return out;
            } else {
                memo.put(sb1.toString(), memo.size());
            }
        }
        return cells;
    }
}
