package leetcode.veryCommon.hardTop50;

import java.util.*;
import java.util.stream.IntStream;

public class NQueens {
    HashSet<Integer> rowSet;
    HashSet<Integer> colSet;
    HashSet<Integer> diagonalRLSet;
    HashSet<Integer> diagonalLRSet;
    HashSet<String> state;
    List<int[]> path;
    List<String> pathString;
    List<List<String>> out;
    int N;

    public List<List<String>> solveNQueens1(int n) {
        rowSet = new HashSet<>();
        colSet = new HashSet<>();
        diagonalRLSet = new HashSet<>();
        diagonalLRSet = new HashSet<>();
        state = new HashSet<>();
        path = new LinkedList<>();
        out = new LinkedList<>();
        N = n;
        helper(n);
        return out;
    }

    //This is O(N^N) complexity since we concentrate on Queen placement in N*N locations N times.
    public void helper(int n) {
        if (n == 0) {
            List<String> tmpOut = new LinkedList<>();
            List<int[]> tmpPath = new ArrayList<>(path);
            tmpPath.sort((a, b) -> a[0] - b[0]);
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();
                int[] row = tmpPath.get(i);
                for (int j = 0; j < N; j++)
                    if (row[1] == j)
                        sb.append("Q");
                    else sb.append(".");
                tmpOut.add(sb.toString());
            }
            if (!state.contains(tmpOut.toString())) {
                state.add(tmpOut.toString());
                out.add(tmpOut);
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!colSet.contains(j) && !rowSet.contains(i) && !diagonalRLSet.contains(i - j) && !diagonalLRSet.contains(i + j)) {
                    colSet.add(j);
                    rowSet.add(i);
                    diagonalRLSet.add(i - j);
                    diagonalLRSet.add(i + j);
                    path.add(new int[]{i, j});
                    helper(n - 1);
                    path.remove(path.size() - 1);
                    colSet.remove(j);
                    rowSet.remove(i);
                    diagonalRLSet.remove(i - j);
                    diagonalLRSet.remove(i + j);
                }
            }
        }
    }

    //This is O(N!) complexity since we concentrate on Row 1 by one hence after each row the number of queens to be placed reduce by 1.
    public List<List<String>> solveNQueensHash(int n) {
        colSet = new HashSet<>();
        diagonalRLSet = new HashSet<>();
        diagonalLRSet = new HashSet<>();
        state = new HashSet<>();
        pathString = new LinkedList<>();
        out = new LinkedList<>();
        N = n;
        helperRow(n);
        return out;
    }

    public void helperRow(int r) {
        if (r == N) out.add(new LinkedList<>(pathString));
        for (int c = 0; c < N; c++) {
            if (!colSet.contains(c) && !diagonalRLSet.contains(r - c) && !diagonalLRSet.contains(r + c)) {
                colSet.add(c);
                diagonalRLSet.add(r - c);
                diagonalLRSet.add(r + c);
                char[] row = new char[N];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                pathString.add(new String(row));
                helperRow(r + 1);
                pathString.remove(pathString.size() - 1);
                colSet.remove(c);
                diagonalRLSet.remove(r - c);
                diagonalLRSet.remove(r + c);
            }
        }
    }

    //This is O(N!) complexity since we concentrate on Row 1 by one hence after each row the number of queens to be placed reduce by 1.
    public List<List<String>> solveNQueensArray(int n) {
        List<List<String>> res = new ArrayList<>();
        helper(0, new boolean[n], new boolean[2 * n], new boolean[2 * n],
                new String[n], res);
        return res;
    }

    private void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2,
                        String[] board, List<List<String>> res) {
        if (r == board.length) res.add(Arrays.asList(board.clone()));
        else {
            for (int c = 0; c < board.length; c++) {
                int id1 = r - c + board.length, id2 = 2 * board.length - r - c - 1;
                if (!cols[c] && !d1[id1] && !d2[id2]) {
                    char[] row = new char[board.length];
                    Arrays.fill(row, '.');
                    row[c] = 'Q';
                    board[r] = new String(row);
                    cols[c] = true;
                    d1[id1] = true;
                    d2[id2] = true;
                    helper(r + 1, cols, d1, d2, board, res);
                    cols[c] = false;
                    d1[id1] = false;
                    d2[id2] = false;
                }
            }
        }
    }
}
