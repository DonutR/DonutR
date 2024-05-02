package leetcode.veryCommon.random;

import java.util.ArrayList;
import java.util.List;

//Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
//
//        Example 1:
//
//        Input: num1 = "2", num2 = "3"
//        Output: "6"
//        Example 2:
//
//        Input: num1 = "123", num2 = "456"
//        Output: "56088"
//        Note:
//
//        The length of both num1 and num2 is < 110.
//        Both num1 and num2 contain only digits 0-9.
//        Both num1 and num2 do not contain any leading zero, except the number 0 itself.
//        You must not use any built-in BigInteger library or convert the inputs to integer directly.
public class MultiplyStrings {
    public static void main(String[] args) {
        multiply("123", "456");
    }

    public static String multiply(String num1, String num2) {
        int maxLen = 0;
        List<Integer>[] mulMatrix = new ArrayList[num1.length()];
        for (int k = 0; k < num1.length(); k++)
            mulMatrix[k] = new ArrayList<>();
        for (int i = num1.length() - 1, ct = 0; i >= 0; i--, ct++) {
            int rem = 0;
            for (int l = 0; l < ct; l++)
                mulMatrix[i].add(0);
            for (int j = num2.length() - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - 48) * (num2.charAt(j) - 48);
                mulMatrix[i].add(mul % 10 + rem);
                rem = mul / 10;
            }
            mulMatrix[i].add(rem);
            if (mulMatrix[i].size() > maxLen)
                maxLen = mulMatrix[i].size();
        }
        StringBuilder sb = new StringBuilder();
        int rem = 0;
        for (int i = 0; i < maxLen; i++) {
            int sum = 0;
            for (List<Integer> arr : mulMatrix) {
                if (arr.size() > i)
                    sum = sum + arr.get(i);
            }
            sum += rem;
            rem = sum / 10;
            sb.append(sum % 10);
        }
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c != 48)
                break;
            else
                sb.deleteCharAt(i);
        }
        String out = sb.reverse().toString();
        if(num1.length()==0||num2.length()==0)
            return "";
        return out.length() > 0 ? out : "0";
    }
}
