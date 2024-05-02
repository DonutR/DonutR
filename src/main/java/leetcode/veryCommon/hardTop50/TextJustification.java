package leetcode.veryCommon.hardTop50;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        Stack<String> stack = new Stack<>();
        List<String> output = new LinkedList<>();
        int currLineLen = 0, curLineWordCt = 0;
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int curWordLen = word.length();
            curLineWordCt++;
            currLineLen += curWordLen;
            if (currLineLen + curLineWordCt - 1 > maxWidth) {
                currLineLen = curWordLen;
                curLineWordCt = 1;
                stack.push(null);
                stack.push(word);
            } else
                stack.push(word);
        }
        boolean isLastLine = true;
        while (!stack.isEmpty()) {
            List<String> curLine = new LinkedList<>();
            int charCt = 0;
            while (!stack.isEmpty() && stack.peek() != null) {
                charCt += stack.peek().length();
                curLine.add(stack.pop());
            }
            if (!stack.isEmpty() && stack.peek() == null) stack.pop();
            Collections.reverse(curLine);
            int padding = maxWidth - charCt;
            int spaceCt = curLine.size() - 1;
            StringBuilder sbPadding = new StringBuilder();
            StringBuilder line = new StringBuilder();
            if (isLastLine) {
                IntStream.range(0, padding - spaceCt).forEach(i -> sbPadding.append(" "));
                line.append(curLine.stream().collect(Collectors.joining(" ")) + sbPadding);
                isLastLine = false;
            } else {
                if (spaceCt > 0) {

                    int spaces = padding / spaceCt, remSpaces = padding % spaceCt;
                    IntStream.range(0, spaces).forEach(i -> sbPadding.append(" "));
                    String rightPad = sbPadding.toString();
                    String leftPad = rightPad + " ";
                    for (String word : curLine) {
                        line.append(word);
                        if (remSpaces > 0) {
                            line.append(leftPad);
                            remSpaces--;
                        } else if (spaceCt > 0)
                            line.append(rightPad);
                        spaceCt--;
                    }
                } else {
                    IntStream.range(0, padding).forEach(i -> sbPadding.append(" "));
                    line.append(curLine.get(0) + sbPadding.toString());
                }
            }
            output.add(line.toString());
        }
        Collections.reverse(output);
        return output;
    }
}
