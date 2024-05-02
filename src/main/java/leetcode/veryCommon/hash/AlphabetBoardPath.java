package leetcode.veryCommon.hash;

import java.util.HashMap;
import java.util.stream.IntStream;

public class AlphabetBoardPath {
    public String alphabetBoardPath(String target) {
        String[] board = {"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
        HashMap<Character, Integer[]> charPosMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].toCharArray().length; j++)
                charPosMap.put(board[i].charAt(j), new Integer[]{i, j});

        Integer[] curPos = {0, 0};
        for (Character c : target.toCharArray()) {
            Integer[] newPos = charPosMap.get(c);
            if (newPos[0] < curPos[0]) IntStream.range(0, curPos[0] - newPos[0]).forEach(i -> sb.append("U"));
            if (newPos[1] > curPos[1]) IntStream.range(0, newPos[1] - curPos[1]).forEach(i -> sb.append("R"));
            if (newPos[1] < curPos[1]) IntStream.range(0, curPos[1] - newPos[1]).forEach(i -> sb.append("L"));
            if (newPos[0] > curPos[0]) IntStream.range(0, newPos[0] - curPos[0]).forEach(i -> sb.append("D"));
            sb.append("!");
            curPos = newPos;
        }
        return sb.toString();
    }
}
