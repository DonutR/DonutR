package leetcode.veryCommon.unionfind;

public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        DSU dsu = new DSU(4 * N * N);
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int root = r * 4 * N;
                char ch = grid[r].charAt(c);
                if (ch == '/') {
                    dsu.union(root + 0, root + 1);
                    dsu.union(root + 2, root + 3);
                } else if (ch == '\\') {
                    dsu.union(root + 1, root + 2);
                    dsu.union(root + 0, root + 3);
                } else if (ch == ' ') {
                    dsu.union(root + 0, root + 1);
                    dsu.union(root + 2, root + 3);
                    dsu.union(root + 1, root + 2);
                    dsu.union(root + 0, root + 3);
                }

                //North South
                if (r - 1 >= 0) {
                    dsu.union(root + 1, root - (4 * N) + 3);
                }
                if (r + 1 < N) {
                    dsu.union(root + 3, root + (4 * N) + 1);
                }
                //East West
                if (c - 1 >= 0) {
                    dsu.union(root + 0, (root - 4) + 2);
                }
                if (c + 1 < N) {
                    dsu.union(root + 2, (root + 4) + 0);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 4 * N * N; i++) {
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
