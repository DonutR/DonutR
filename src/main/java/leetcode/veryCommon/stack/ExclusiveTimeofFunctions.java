package leetcode.veryCommon.stack;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeofFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<String[]> stack = new Stack<String[]>();
        int[] out = new int[n];
        int lastEnd = 0;
        stack.push(logs.get(0).split(":"));
        for (int i = 1; i < logs.size(); i++) {
            if (stack.isEmpty()) {
                stack.push(logs.get(i).split(":"));
                continue;
            }
            String[] curr = stack.peek();
            String[] next = logs.get(i).split(":");
            if (next[1].equals("start")) {
                int currId = Integer.parseInt(curr[0]);
                int curTime = 0;
                if (lastEnd <= Integer.parseInt(curr[2]))
                    curTime = Integer.parseInt(next[2]) - Integer.parseInt(curr[2]);
                else curTime = Integer.parseInt(next[2]) - (lastEnd + 1);
                out[currId] += curTime;
                stack.push(next);
            } else {
                int currId = Integer.parseInt(curr[0]);
                int curTime = 0;
                if (lastEnd < Integer.parseInt(curr[2]))
                    curTime = Integer.parseInt(next[2]) - Integer.parseInt(curr[2]) + 1;
                else curTime = Integer.parseInt(next[2]) - lastEnd;
                out[currId] += curTime;
                lastEnd = Integer.parseInt(next[2]);
                stack.pop();
            }
        }
        return out;
    }
}
