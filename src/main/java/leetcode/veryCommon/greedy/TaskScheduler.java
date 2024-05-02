package leetcode.veryCommon.greedy;

import java.util.Arrays;

public class TaskScheduler {
    public static void main(String[] args) {
    }

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] taskCt = new int[26];
        int out = 0;
        for (char c : tasks)
            taskCt[c - 'A']++;

        Arrays.sort(taskCt);
        int totalTaskCt = tasks.length;
        while (totalTaskCt > 0) {
            int blockSize = 0;
            while (blockSize <= n && totalTaskCt > 0) {
                if (blockSize < 26 && taskCt[25 - blockSize] > 0) {
                    taskCt[25 - blockSize]--;
                    totalTaskCt--;
                }
                out++;
                blockSize++;
            }
            Arrays.sort(taskCt);
        }
        return out;
    }
}
