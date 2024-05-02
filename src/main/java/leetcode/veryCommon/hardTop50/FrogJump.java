package leetcode.veryCommon.hardTop50;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class FrogJump {
    public boolean canCross(int[] stones) {
        HashSet<Integer> stonesPos = new HashSet<>();
        HashMap<Integer, HashMap<Integer, Boolean>> memo = new HashMap<>();
        Arrays.stream(stones).forEach(i -> stonesPos.add(i));
        return helper(stonesPos, 0, 0, memo, stones[stones.length - 1]);
    }

    public boolean helper(HashSet<Integer> stonesPos, int idx, int prevJmpCt, HashMap<Integer, HashMap<Integer, Boolean>> memo, int max) {
        System.out.println(idx + "   " + prevJmpCt);
        if (idx < 0 || (idx > 0 && prevJmpCt == 0) || !stonesPos.contains(idx) || idx > max) {
            memo.computeIfAbsent(idx, x -> new HashMap<>()).computeIfAbsent(prevJmpCt, x -> false);
            return false;
        } else if (idx == max) {
            memo.computeIfAbsent(idx, x -> new HashMap<>()).computeIfAbsent(prevJmpCt, x -> true);
            return true;
        } else if (memo.containsKey(idx) && memo.get(idx).containsKey(prevJmpCt))
            return memo.get(idx).get(prevJmpCt);

        boolean fwd = helper(stonesPos, idx + prevJmpCt + 1, prevJmpCt + 1, memo, max);
        if (fwd) return fwd;
        boolean bwd = helper(stonesPos, idx + prevJmpCt - 1, prevJmpCt - 1, memo, max);
        if (bwd) return bwd;
        if (prevJmpCt != 0)
            return helper(stonesPos, idx + prevJmpCt, prevJmpCt, memo, max);
        return false;
    }
}
