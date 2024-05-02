package leetcode.veryCommon.trie;

class Trie {
    TrieNode root;

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

    /**
     * Returns if the word is in the trie.
     */
    public TrieNode searchPrefix(String key) {
        TrieNode curNode = root;
        for (int i = 0; i < key.length(); i++) {
            char curChar = key.charAt(i);
            if (curNode.containKey(curChar))
                curNode = curNode.get(curChar);
            else return null;
        }
        return curNode;
    }

    public boolean search(String key) {
        TrieNode lastNode = searchPrefix(key);
        return lastNode != null && lastNode.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String key) {
        TrieNode lastNode = searchPrefix(key);
        return lastNode != null;
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

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

