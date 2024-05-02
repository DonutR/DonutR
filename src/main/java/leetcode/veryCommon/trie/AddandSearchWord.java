package leetcode.veryCommon.trie;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddandSearchWord {
    class WordDictionary {
        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void addWord(String key) {
            TrieNode curNode = root;
            for (int i = 0; i < key.length(); i++) {
                char curChar = key.charAt(i);
                if (curNode.containKey(curChar))
                    curNode = curNode.get(curChar);
                else {
                    curNode.put(curChar);
                    curNode = curNode.get(curChar);
                }
            }
            curNode.setEnd(true);
        }

        /**
         * Returns if the pattern is in the trie.
         */
        public boolean searchWithDotHelper(String s, TrieNode head) {
            if (s.length() == 0)
                return head.isEnd;
            for (int i = 0; i < s.length() && head != null; i++) {
                char c = s.charAt(i);
                if (c == '.') {
                    List<TrieNode> headList = Arrays.stream(head.childNodes).filter(Objects::nonNull).collect(Collectors.toList());
                    for (TrieNode n : headList) {
                        boolean result = searchWithDotHelper(s.substring(i + 1), n);
                        if (result == true) return true;
                    }
                    return false;
                } else
                    head = head.get(c);
            }
            return (head != null && head.isEnd);
        }

        public boolean search(String key) {
            return searchWithDotHelper(key, root);
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
}
