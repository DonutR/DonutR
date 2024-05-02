package leetcode.veryCommon.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PartitionLabels {
    //Greedy solution
    //We need an array last[char] -> index of S where char occurs last.
    //Then, let anchor and j be the start and end of the current partition.
    // If we are at a label that occurs last at some index after j, we'll extend the partition j = last[c].
    // If we are at the end of the partition (i == j) then we'll append a partition size to our answer, and set the start of our new partition to i+1.
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    public List<Integer> partitionLabelsStack(String S) {
        HashMap<Character, Integer[]> map = new HashMap<>();
        int i = 0;
        for (char c : S.toCharArray()) {
            if (map.containsKey(c)) {
                map.get(c)[1] = i;
            } else {
                Integer[] tmp = {i, i};
                map.put(c, tmp);
            }
            i++;
        }

        List<Integer[]> spanList = map.values().stream().sorted((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            else return a[0] - b[0];
        }).collect(Collectors.toList());

        Stack<Integer[]> merge = new Stack<>();
        merge.push(spanList.get(0));

        for (Integer[] tmp : spanList) {
            Integer[] top = merge.peek();
            if (top[0] <= tmp[0] && top[1] >= tmp[0]) {
                merge.pop();
                Integer[] tmpTop = {top[0], Math.max(top[1], tmp[1])};
                merge.push(tmpTop);
            } else merge.push(tmp);

        }

        return merge.stream().map(a -> (a[1] - a[0]) + 1).collect(Collectors.toList());
    }
}
