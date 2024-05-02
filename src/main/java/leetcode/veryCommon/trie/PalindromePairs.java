package leetcode.veryCommon.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> wordToIdxMp = new HashMap<>();
        List<List<Integer>> out = new LinkedList<>();
        IntStream.range(0, words.length).forEach(i -> wordToIdxMp.put(words[i], i));
        Arrays.stream(words).filter(word -> word.length() == 0).forEach(word -> {
            Arrays.stream(words)
                    .filter(word2 -> isPal(word2) && !word2.equals(""))
                    .forEach(word2 ->
                            out.add(Arrays.asList(new Integer[]{wordToIdxMp.get(word2), wordToIdxMp.get("")})));
        });
        for (String word : words) {
            for (int i = 1; i <= word.length(); i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(i);
                String prefixRev = reverse(prefix);
                String suffixRev = reverse(suffix);
                if (wordToIdxMp.containsKey(prefixRev) && isPal(suffix) && !word.equals(prefixRev))
                    out.add(Arrays.asList(new Integer[]{wordToIdxMp.get(word), wordToIdxMp.get(prefixRev)}));
                if (wordToIdxMp.containsKey(suffixRev) && isPal(prefix) && !word.equals(suffixRev))
                    out.add(Arrays.asList(new Integer[]{wordToIdxMp.get(suffixRev), wordToIdxMp.get(word)}));
            }
        }
        return out;
    }

    public boolean isPal(String s) {
        StringBuilder remSb = new StringBuilder();
        remSb.append(s);
        remSb.reverse();
        return s.equals(remSb);
    }

    public String reverse(String s) {
        StringBuilder nodeString = new StringBuilder();
        nodeString.append(s);
        nodeString.reverse();
        return nodeString.toString();
    }

    public List<List<Integer>> palindromePairs1(String[] words) {
        TrieNode root = new TrieNode();
        HashMap<String, Integer> wordToIdxMp = new HashMap<>();
        List<List<Integer>> out = new LinkedList<>();
        IntStream.range(0, words.length).forEach(i -> wordToIdxMp.put(words[i], i));
        for (String word : words) {
            TrieNode curNode = root;
            for (int i = 0; i < word.length(); i++) {
                char curChar = word.charAt(i);
                if (curNode.containKey(curChar))
                    curNode = curNode.get(curChar);
                else {
                    curNode.put(curChar);
                    curNode = curNode.get(curChar);
                }
            }
            curNode.setEnd(true);
        }
        if (wordToIdxMp.containsKey("")) {
            int idx = wordToIdxMp.get("");
            for (String word2 : words) {
                if (isPal(word2) && !word2.equals("")) {
                    int idx2 = wordToIdxMp.get(word2);
                    out.add(Arrays.asList(new Integer[]{idx2, idx}));
                    out.add(Arrays.asList(new Integer[]{idx, idx2}));
                }
            }
        }

        for (String word : words) {
            TrieNode currNode = root;
            StringBuilder currSb = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                if (!currNode.containKey(c))
                    break;
                currSb.append(c);
                String currSt = currSb.toString();
                currNode = currNode.get(c);
                String rem = word.substring(0, i);
                if (isPal(rem) && !currSt.equals(word) && currNode.isEnd)
                    out.add(Arrays.asList(new Integer[]{wordToIdxMp.get(currSt), wordToIdxMp.get(word)}));
            }
            currSb = new StringBuilder();
            currNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!currNode.containKey(c))
                    break;
                currSb.append(c);
                String currSt = currSb.toString();
                currNode = currNode.get(c);
                String rem = word.substring(i + 1);
                System.out.println(rem);
                if (rem.length() > 0 && isPal(rem) && reverse(currSt).equals(currSt) && wordToIdxMp.containsKey(currSt))
                    out.add(Arrays.asList(new Integer[]{wordToIdxMp.get(word), wordToIdxMp.get(currSt)}));
            }
        }
        return out;
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
