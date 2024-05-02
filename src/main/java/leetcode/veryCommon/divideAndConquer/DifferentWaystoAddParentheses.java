package leetcode.veryCommon.divideAndConquer;

import java.util.*;
import java.util.stream.Collectors;

public class DifferentWaystoAddParentheses {
    List<Integer> out = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }

    public static List<Integer> diffWaysToCompute(String input) {
        ArrayList<String> splitList = split(input);
        List<Integer> ret = new LinkedList<Integer>();
        int sz = splitList.size();
        for (int i = 0; i < sz; i++) {
            if (splitList.get(i).equals("-") || splitList.get(i).equals("+") || splitList.get(i).equals("*")) {
                List<Integer> part1Ret = diffWaysToCompute(splitList.subList(0, i).stream().collect(Collectors.joining()));
                List<Integer> part2Ret = diffWaysToCompute(splitList.subList(i + 1, sz).stream().collect(Collectors.joining()));
                for (Integer a : part1Ret)
                    for (Integer b : part2Ret) {
                        int res = 0;
                        switch (splitList.get(i)) {
                            case "+":
                                res = a + b;
                                break;
                            case "-":
                                res = a - b;
                                break;
                            case "*":
                                res = a * b;
                                break;
                        }
                        ret.add(res);
                    }
            }
        }
        if (ret.size() == 0)
            ret.add(Integer.parseInt(input));
        return ret;
    }

    public static ArrayList<String> split(String input) {
        ArrayList<String> ret = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        boolean isPrevCharOpt = false;
        int i = 0;
        if (input.charAt(0) < '0' || input.charAt(0) > '9') {
            tmp.append(input.charAt(0));
            isPrevCharOpt = true;
            i++;
        }
        for (; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isPrevCharOpt) {
                if (c >= '0' && c <= '9') {
                    tmp.append(input.charAt(i));
                    isPrevCharOpt = false;
                } else {
                    ret.add(tmp.toString());
                    isPrevCharOpt = true;
                    tmp = new StringBuilder();
                    tmp.append(input.charAt(i));
                    ret.add(tmp.toString());
                    tmp = new StringBuilder();
                }
            } else {
                tmp.append(input.charAt(i));
                isPrevCharOpt = false;
            }
        }
        ret.add(tmp.toString());
        return ret;
    }
}
