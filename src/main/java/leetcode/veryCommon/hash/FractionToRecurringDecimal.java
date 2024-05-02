package leetcode.veryCommon.hash;

import java.util.HashMap;
import java.util.stream.Collectors;

public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(2, -5));
    }

    public static String fractionToDecimal1(int numerator, int denominator) {
        if (denominator == 0 || numerator == 0)
            return "0";
        String resultInt = numerator < 0 && denominator >= 0 || numerator >= 0 && denominator < 0 ? "-" : "";
        long deno = Math.abs((long) denominator);
        long nume = Math.abs((long) numerator);
        long dev = nume / deno;
        resultInt = resultInt + (dev == 0 ? 0 : dev) + "";

        long rem = nume % deno;
        System.out.println(rem);
        HashMap<Long, Integer> remSet = new HashMap<>();
        remSet.put(rem, 0);
        StringBuilder sb = new StringBuilder();
        int ct = 0;
        while (true) {
            if (rem == 0)
                return sb.length() > 0 ? resultInt + "." + sb.toString() : resultInt;
            long newNum = rem * 10;
            dev = newNum / deno;
            sb.append(dev == 0 ? 0 : dev);
            rem = newNum % deno;
            if (remSet.containsKey(rem)) {
                return resultInt + "." + sb.substring(0, remSet.get(rem)) + "(" + sb.substring(remSet.get(rem)) + ")";
            }
            remSet.put(rem, ++ct);
        }
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0 || numerator == 0)
            return "0";
        String sign = (numerator < 0 && denominator < 0) || (numerator > 0 && denominator > 0) ? "" : "-";
        long d = Math.abs((long) denominator);
        long n = Math.abs((long) numerator);
        int val = (int) Math.abs(n / d);
        System.out.println(val);
        HashMap<Long, Integer> remIdx = new HashMap<>();
        int idx = 0;
        long rem = (n % d) * 10;
        StringBuilder fractionSb = new StringBuilder();
        while (rem > 0 && !remIdx.containsKey(rem) && rem < Long.MAX_VALUE) {
            remIdx.put(rem, idx);
            fractionSb.append(rem / d);
            idx++;
            rem = (rem % d) * 10;
        }
        if (rem != 0 && rem < Long.MAX_VALUE) {
            fractionSb.insert(remIdx.get(rem), "(").append(")");
        }
        return sign + val + (fractionSb.length() > 0 ? "." + fractionSb.toString() : "");
    }
}
