package leetcode.veryCommon.unionfind;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TheEarliestMomentWhenEveryoneBecomeFriends {
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UnionFindDs ds = new UnionFindDs(n);
        for (int i = 0; i < logs.length; i++) {
            ds.union(logs[i][1], logs[i][2]);
            if (ds.count == 1)
                return logs[i][0];
        }
        return -1;
    }

    public class UnionFindDs {
        int[] parent;
        int n;
        int count;

        public UnionFindDs(int n) {
            this.parent = new int[n];
            this.n = n;
            this.count = n;
            for (int i = 0; i < n; i++)
                this.parent[i] = i;
        }

        public int find(int a) {
            if (this.parent[a] != a)
                this.parent[a] = find(this.parent[a]);
            return this.parent[a];
        }

        public void union(int a, int b) {
            int bParent = find(b);
            int aParent = find(a);
            if (bParent != aParent) {
                count--;
                this.parent[bParent] = aParent;
            }
        }
    }
}
