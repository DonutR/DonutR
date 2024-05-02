package leetcode.veryCommon.unionfind;

import java.util.stream.IntStream;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        UnionFindDs ufds = new UnionFindDs(n);
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (ufds.find(a) == ufds.find(b)) return false;
            ufds.union(a, b);
        }
        return IntStream
                .range(0, n)
                .map(i -> ufds.find(i))
                .distinct()
                .count() == 1 ?
                true : false;
    }

    public class UnionFindDs {
        int[] parent;
        int n;

        public UnionFindDs(int n) {
            this.parent = new int[n];
            this.n = n;
            for (int i = 0; i < n; i++)
                this.parent[i] = i;
        }

        public int find(int a) {
            if (this.parent[a] != a)
                this.parent[a] = find(this.parent[a]);
            return this.parent[a];
        }

        public void union(int a, int b) {
            this.parent[find(b)] = find(a);
        }
    }
}
