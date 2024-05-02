package leetcode.veryCommon.hardTop50;

import java.util.*;
import java.util.stream.IntStream;

public class JumpGame4 {
    public int minJumps(int[] arr) {
        int N = arr.length;
        Map<Integer, List<Integer>> valueIdxMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        IntStream.range(0, N).forEach(i -> valueIdxMap.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i));
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(null);
        int out = 0;

        while (!q.isEmpty()) {
            Integer currNode = q.poll();
            if (currNode == null) {
                out++;
                if (!q.isEmpty())
                    q.add(null);
            } else if (currNode == N - 1)
                return out + 1;
            else {
                visited.add(currNode);
                List<Integer> nextNodes = valueIdxMap.get(arr[currNode]);
                nextNodes.add(currNode - 1);
                nextNodes.add(currNode + 1);

                for (Integer nextNode : nextNodes)
                    if (nextNode >= 0 && nextNode < N && !visited.contains(nextNode))
                        q.add(nextNode);
            }
        }
        return -1;
    }
}
