package leetcode.veryCommon.hardTop50;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        return helper(0, 0, 0, s1, s2, s3);
    }

    public boolean helper(int i, int j, int k, String s1, String s2, String s3) {
        if (s1.length() == i && s2.length() == j && s3.length() == k)
            return true;

        if (s1.length() > i && s2.length() > j && s3.length() > k && s1.charAt(i) == s3.charAt(k) && s2.charAt(j) == s3.charAt(k)) {
            boolean ret = helper(i + 1, j, k + 1, s1, s2, s3);
            if (!ret) ret = helper(i, j + 1, k + 1, s1, s2, s3);
            return ret;
        } else if (s1.length() > i && s3.length() > k && s1.charAt(i) == s3.charAt(k))
            return helper(i + 1, j, k + 1, s1, s2, s3);
        else if (s2.length() > j && s3.length() > k && s2.charAt(j) == s3.charAt(k))
            return helper(i, j + 1, k + 1, s1, s2, s3);
        else return false;
    }
}
