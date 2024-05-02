package leetcode.veryCommon.array;

import com.google.common.io.CharStreams;

import java.util.*;
import java.util.stream.IntStream;

public class NumberofMatchingSubsequences {
    //Tree Set
    public int numMatchingSubseq1(String S, String[] words) {
        TreeSet<Integer>[] dict = new TreeSet[26];
        int out = 0;
        for (int i = 0; i < 26; i++)
            dict[S.charAt(i) - 'a'] = new TreeSet<>();
        for (int i = 0; i < S.length(); i++)
            dict[S.charAt(i) - 'a'].add(i);
        for (String word : words) {
            int subSeqHead = 0, i = 0;
            for (; i < word.length(); i++) {
                char c = word.charAt(i);
                Integer tmpHead = dict[c - 'a'].ceiling(subSeqHead);
                if (tmpHead == null)
                    break;
                subSeqHead = tmpHead + 1;
            }
            if (i == word.length()) out++;
        }
        return out;
    }

    //Map of head character
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, LinkedList<String>> headMap = new HashMap<>();
        IntStream.range(1, 26).forEach(i -> headMap.put((char) (i + 'a'), new LinkedList<>()));
//        For input
//        S = "abcde"
//        words = ["a", "bb", "acd", "ace"]
//        Map-> {a->{a,acd,ace},b->{b}}
        Arrays.stream(words).forEach(s -> headMap.get(s.charAt(0)).add(s));
        int out = 0;
        for (char c : S.toCharArray()) {
            Queue<String> curCharQ = headMap.get(c);
            int size = curCharQ.size();
            for (int i = 0; i < size; i++) {
                String word = curCharQ.poll();
                if (word.length() == 1) {
                    out++;
                } else {
                    headMap.get(word.charAt(1)).addLast(word.substring(1));
                }
            }
        }
        return out;
    }
}
