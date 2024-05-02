package leetcode.veryCommon.trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WordSearchII {
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    TrieNode root;
    int m, n;
    char[][] board;
    HashSet<String> out;

    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        this.board = board;
        m = this.board.length;
        n = this.board[0].length;
        this.out = new HashSet<>();
        Arrays.stream(words).forEach(s -> this.insert(s));

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dfs(i, j, root, new StringBuilder(), new boolean[m][n]);

        return new LinkedList<>(out);
    }

    public void dfs(int i, int j, TrieNode root, StringBuilder sb, boolean[][] visited) {
        if (!root.containKey(board[i][j]))
            return;

        System.out.println("In DFS");
        visited[i][j] = true;
        sb.append(board[i][j]);

        TrieNode nextNode = root.get(board[i][j]);
        if (nextNode.isEnd()) out.add(sb.toString());

        for (int[] ij : dir) {
            int nI = i + ij[0], nJ = j + ij[1];
            if (nI >= 0 && nI < board.length && nJ >= 0 && nJ < board[0].length && !visited[nI][nJ]) {
                dfs(nI, nJ, nextNode, sb, visited);
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
    }

    public void insert(String key) {
        TrieNode curNode = this.root;
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
