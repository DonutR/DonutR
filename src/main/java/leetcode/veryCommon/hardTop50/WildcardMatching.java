package leetcode.veryCommon.hardTop50;

public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println("****c***p***x".replaceAll("[\\*]", "_"));
    }

    public boolean isMatch(String s, String p) {
        boolean onlyStar = true;
        for (char pc : p.toCharArray())
            if (pc != '*')
                onlyStar = false;
        if (s.length() == 0)
            if (p.length() == 0 || onlyStar)
                return true;
            else
                return false;
        else if (s.length() > 0 && p.length() == 0)
            return false;

        if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')
            return isMatch(s.substring(1), p.substring(1));
        else if (p.charAt(0) == '*') {
            int i = 0;
            while (i + 1 < p.length() && p.charAt(i + 1) == '*')
                i++;
            p = p.substring(i);
            if (p.length() > 1) {
                int ct = 0;
                for (char c : s.toCharArray()) {
                    if (c == p.charAt(1) || p.charAt(1) == '?') {
                        if (isMatch(s.substring(ct), p.substring(1)))
                            return true;
                    }
                    ct++;
                }
                return false;
            } else return true;
        }
        return false;
    }
}