package leetcode.veryCommon.design;

import java.util.Arrays;

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
        public boolean searchPattern(TrieNode root, int idx, String key) {
            if (root == null)
                return false;
            else if (idx == key.length() - 1) {
                if (key.charAt(idx) == '.')
                    return Arrays.stream(root.childNodes).filter(a -> a != null && a.isEnd).count() > 0;
                else
                    return root.get(key.charAt(idx)) != null && root.get(key.charAt(idx)).isEnd;
            } else {
                boolean ret = false;
                if (key.charAt(idx) == '.') {
                    for (int i = 0; i < 26; i++) {
                        ret = searchPattern(root.childNodes[i], idx + 1, key);
                        if (ret == true)
                            return true;
                    }
                } else
                    ret = searchPattern(root.get(key.charAt(idx)), idx + 1, key);
                return ret;
            }
        }

        public boolean search(String key) {
            return searchPattern(root, 0, key);
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
