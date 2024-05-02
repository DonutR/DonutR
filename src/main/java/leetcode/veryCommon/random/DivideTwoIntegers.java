package leetcode.veryCommon.random;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(divide(2147483647, 10));
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0)
            return 0;
        int resultSign = dividend < 0 && divisor >= 0 || dividend >= 0 && divisor < 0 ? -1 : 1;
        int nume = dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(dividend);
        int deno = Math.abs(divisor);
        int result = resultSign * recFun(nume, deno);
        return result;
    }

    public static int recFun(int nume, int deno) {
        if (nume >= deno) {
            int multiple = 1;
            int sum = deno;
            while ((long) sum + sum > Integer.MIN_VALUE && (long) sum + sum <= Integer.MAX_VALUE && sum + sum < nume) {
                sum += sum;
                System.out.println(sum);
                multiple += multiple;
            }
            return multiple + recFun(nume - sum, deno);
        }
        return 0;
    }
}
