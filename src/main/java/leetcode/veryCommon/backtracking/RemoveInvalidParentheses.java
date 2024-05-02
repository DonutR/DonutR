package leetcode.veryCommon.backtracking;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveInvalidParentheses {
    HashSet<String> output;

    public List<String> removeInvalidParentheses(String s) {
        int[] invalidCt = invalidParenthesesCt(s);
        output = new HashSet<>();
        helper(s, 0, 0, 0, invalidCt[0], invalidCt[1], new StringBuilder());
        return output.stream().collect(Collectors.toList());
    }

    public void helper(String s, int index, int leftCount, int rightCount,
                       int leftRem, int rightRem, StringBuilder currExpression) {
        int exprOrigLength = currExpression.length();
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0)
                output.add(currExpression.length() == 0 ? "" : currExpression.toString());
        } else {
            if (s.charAt(index) != ')' && s.charAt(index) != '(')
                helper(s, index + 1, leftCount, rightCount, leftRem,
                        rightRem, currExpression.append(s.charAt(index)));
            else {
                if (s.charAt(index) == '(') {
                    if (leftRem > 0)
                        helper(s, index + 1, leftCount, rightCount, leftRem - 1,
                                rightRem, currExpression);
                    helper(s, index + 1, leftCount + 1, rightCount, leftRem,
                            rightRem, currExpression.append(s.charAt(index)));
                } else if (s.charAt(index) == ')') {
                    if (rightRem > 0)
                        helper(s, index + 1, leftCount, rightCount, leftRem,
                                rightRem - 1, currExpression);
                    if (rightCount < leftCount)
                        helper(s, index + 1, leftCount, rightCount + 1, leftRem,
                                rightRem, currExpression.append(s.charAt(index)));
                }
            }
            if (currExpression.length() > exprOrigLength)
                currExpression.deleteCharAt(currExpression.length() - 1);
        }
    }

    public int[] invalidParenthesesCt(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else if (s.charAt(i) == ')')
                if (left > 0)
                    left--;
                else right++;
        }
        return new int[]{left, right};
    }
}
