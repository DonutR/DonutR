package leetcode.veryCommon.hardTop50;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        String finalState = "123450";
        String start = "";

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                start += board[i][j];

        if (finalState.equals(start)) return 0;

        Queue<String> queue = new LinkedList<>();
        HashSet<String> isVisited = new HashSet<>();

        int[][] possibleWays = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

        int result = 0;

        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curString = queue.poll();
                int zeroPosition = curString.indexOf('0');
                for (int newPosition : possibleWays[zeroPosition]) {
                    String newString = swap(curString, zeroPosition, newPosition);
                    if (newString.equals(finalState))
                        return result + 1;
                    if (isVisited.contains(newString))
                        continue;
                    isVisited.add(newString);
                    queue.add(newString);
                }
            }
            result++;
        }
        return -1;
    }

    public String swap(String curString, int curPos, int newPos) {
        char[] charArr = curString.toCharArray();
        char tmp = charArr[curPos];
        charArr[curPos] = charArr[newPos];
        charArr[newPos] = tmp;
        return new String(charArr);
    }
}
