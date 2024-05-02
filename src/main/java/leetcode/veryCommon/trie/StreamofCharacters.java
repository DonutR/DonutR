package leetcode.veryCommon.trie;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StreamofCharacters {
    class StreamChecker {
        Trie trie = new Trie();

        public StreamChecker(String[] words) {
            Arrays.stream(words).forEach(s -> trie.insert(s));
        }

        public boolean query(char letter) {
            return trie.search(letter);
        }

        class Trie {
            TrieNode root;

            List<TrieNode> streamSearchCache = new LinkedList<>();

            /**
             * Initialize your data structure here.
             */
            public Trie() {
                root = new TrieNode();
            }

            /**
             * Inserts a word into the trie.
             */
            public void insert(String key) {
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

            public boolean search(char c) {
                streamSearchCache.add(root);
                List<TrieNode> newCache = new LinkedList<>();
                boolean res = false;
                for (TrieNode n : streamSearchCache) {
                    if (n.containKey(c)) {
                        TrieNode next = n.get(c);
                        newCache.add(next);
                        res = res || next.isEnd();
                    }
                }
                streamSearchCache = newCache;
                return res;
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
}
