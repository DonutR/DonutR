package leetcode.veryCommon.backtracking;

public class ThekthLexicographicalStringofAllHappyStringsofLengthn {
    int n, k, ct = 0;
    String out;

    public String getHappyString(int n, int k) {
        this.n = n;
        this.k = k;
        out = "";
        StringBuilder sb = new StringBuilder();
        sb.append("x");
        recFun(sb);
        return out;
    }

    public void recFun(StringBuilder sb) {
        if (sb.length() == n + 1) {
            ct++;
            if (ct == k)
                out = sb.deleteCharAt(0).toString();
        } else if (sb.length() < n + 1 && ct < k) {
            char l = sb.charAt(sb.length() - 1);
            for (int i = 'a'; i < 'd'; i++) {
                char x = (char) i;
                if (x != l) {
                    recFun(sb.append(x));
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }
}
