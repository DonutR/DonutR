package leetcode.veryCommon.unionfind;

public class NumberofOperationstoMakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        DSU dsu = new DSU(n);
        int cycleCt = 0;
        for (int[] ed : connections) {
            int a = dsu.find(ed[0]);
            int b = dsu.find(ed[1]);
            if (a == b)
                cycleCt++;
            else dsu.union(ed[0], ed[1]);
        }

        int dssCt = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i)
                dssCt++;
        }
        return cycleCt >= dssCt-1 ? dssCt - 1 : -1;
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
