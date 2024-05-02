package leetcode.veryCommon.graph;

import java.util.*;

public class OpentheLock {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadEndSet = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        Arrays.stream(deadends).forEach(s -> deadEndSet.add(s));
        if (deadEndSet.contains("0000")) return -1;
        Queue<String> q = new LinkedList<>();
        q.add("0000");
        q.add(null);
        int count = 0;
        while (!q.isEmpty()) {
            String currComb = q.poll();
            if (currComb == null) {
                count++;
                if (!q.isEmpty()) q.add(null);
            } else if (!visited.contains(currComb)) {
                visited.add(currComb);
                if (currComb.equals(target))
                    return count;
                q.addAll(generateCombinations(currComb, deadEndSet, visited));
            }
        }
        return -1;
    }

    public static List<String> generateCombinations(String seed, HashSet<String> deadEndSet, HashSet<String> visited) {
        List<String> out = new LinkedList<>();
        char[] seedArr = seed.toCharArray();
        for (int j = 0; j < 4; j++) {
            char old = seedArr[j];
            char[] newArr = Arrays.copyOf(seedArr, 4);
            newArr[j] = (char) (old == '0' ? 57 : old - 1);
            String newSeed = new String(newArr);
            if (!deadEndSet.contains(newSeed) && !visited.contains(newSeed)) out.add(newSeed);
            newArr = Arrays.copyOf(seedArr, 4);
            newArr[j] = (char) (old == '9' ? 48 : old + 1);
            newSeed = new String(newArr);
            if (!deadEndSet.contains(newSeed) && !visited.contains(newSeed)) out.add(newSeed);
        }
        return out;
    }
}
