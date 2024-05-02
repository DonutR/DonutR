package leetcode.veryCommon.hash;

import java.util.HashSet;

public class JewelsandStones {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> jewels = new HashSet<>();
        int out = 0;
        for (Character c : J.toCharArray())
            jewels.add(c);
        for (Character c : S.toCharArray())
            if (jewels.contains(c)) out++;
        return out;
    }
}
