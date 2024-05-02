package leetcode.veryCommon.hardTop50;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ConcatenatedWords {
    //DFS
    public List<String> findAllConcatenatedWordsInADict1(String[] words) {
        HashSet<String> dict = new HashSet<>();
        Arrays.stream(words).forEach(s -> dict.add(s));
        List<String> result = new LinkedList<>();
        for (String s : words)
            if (helper(s, dict)) result.add(s);
        return result;
    }

    public boolean helper(String word, HashSet<String> words) {
        int n = word.length();
        //System.out.println(word);
        if (n > 0) {
            for (int i = 1; i < n; i++) {
                String left = word.substring(0, i);
                String right = word.substring(i, n);
                if (words.contains(left) && words.contains(right))
                    return true;
                if (words.contains(right) && helper(left, words))
                    return true;
                if (words.contains(left) && helper(right, words))
                    return true;
            }
        }
        return false;
    }

    HashSet<String> dict = new HashSet<>();
    TrieNode trie = new TrieNode();

    //TRIE
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new LinkedList<>();
        Arrays.stream(words).forEach(s -> {
            addWord(s, trie);
            dict.add(s);
        });
        Arrays.stream(words).filter(s -> testWord(s, s)).forEach(s -> result.add(s));
        return result;
    }

    public boolean testWord(String s, String originalTarget) {
        TrieNode cur = this.trie;
        int n = s.length();
        //Used to avoid same string substring scenario.
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cur.get(c) == null)
                return false;
            else if (cur.get(c).isEnd) {
                if (i == n - 1 && !s.equals(originalTarget))
                    return true;
                if (testWord(s.substring(i + 1, n), originalTarget))
                    return true;
            }
            cur = cur.get(c);
        }
        return false;
    }

    public void addWord(String str, TrieNode root) {
        TrieNode cur = root;
        for (char c : str.toCharArray()) {
            if (cur.get(c) == null)
                cur.put(c);
            cur = cur.get(c);
        }
        cur.setEnd(true);
    }

    class TrieNode {
        TrieNode[] childNodes;
        int R = 26;
        boolean isEnd;

        public TrieNode() {
            childNodes = new TrieNode[R];
        }

        public boolean containKey(char c) {
            return childNodes[c - 'a'] != null ? true : false;
        }

        public void put(char c) {
            childNodes[c - 'a'] = new TrieNode();
        }

        public TrieNode get(char c) {
            return childNodes[c - 'a'];
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
