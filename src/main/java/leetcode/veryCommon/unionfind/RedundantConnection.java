package leetcode.veryCommon.unionfind;

import java.util.Arrays;

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int len = Arrays.stream(edges).max((a, b) -> a[1] - b[1]).get()[0] + 1;
        System.out.println(len);
        DSU dsu = new DSU(len);
        for (int[] ed : edges) {
            int a = dsu.find(ed[0]);
            int b = dsu.find(ed[1]);
            if (a == b)
                return ed;
            else
                dsu.union(ed[0], ed[1]);
        }
        return new int[]{3, 4};
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
