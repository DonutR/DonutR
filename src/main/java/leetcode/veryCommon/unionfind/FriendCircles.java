package leetcode.veryCommon.unionfind;

public class FriendCircles {
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }


    public int findCircleNumDFS(int[][] M) {
        int n = M.length;
        int out = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    M[i][j] = 0;
                    M[j][i] = 0;
                    helper(M, j);
                    out++;
                }
            }
        }
        return out;
    }

    public void helper(int[][] M, int j) {
        for (int k = 0; k < M.length; k++)
            if (M[j][k] == 1) {
                M[j][k] = 0;
                M[k][j] = 0;
                helper(M, k);
            }
    }

    public int findCircleNumDSU(int[][] M) {
        int N = M.length;
        DSU dsu = new DSU(N);
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                if (M[r][c] == 1)
                    dsu.union(r, c);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            //if (DSU.find(i) == i) and  if (DSU.parent[i] == i) gives same result
            if (dsu.find(i) == i)
                ans++;
        }
        return ans;
    }

    class DSU {
        int[] parent;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        public int find(int n) {
            if (parent[n] != n)
                parent[n] = find(parent[n]);
            return parent[n];
        }

        public void union(int n1, int n2) {
            parent[find(n1)] = find(n2);
        }
    }
}
