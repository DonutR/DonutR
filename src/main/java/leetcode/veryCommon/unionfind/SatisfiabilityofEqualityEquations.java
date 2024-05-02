package leetcode.veryCommon.unionfind;

public class SatisfiabilityofEqualityEquations {
    public static void main(String[] args) {
        String[] eq = {"a==b", "b==c", "a==c"};
        String[] eq2 = {"a==b", "b!=a"};
        String[] eq3 = {"b==a", "a==b"};
        String[] eq4 = {"a==b", "b!=c", "c==a"};

//        System.out.println(equationsPossible(eq));
//        System.out.println(equationsPossible(eq2));
//        System.out.println(equationsPossible(eq3));
        //System.out.println(equationsPossible(eq4));
    }

    public boolean equationsPossible(String[] equations) {
        DSU dsu = new DSU(26);
        for (String eq : equations) {
            if (eq.charAt(1) == '=')
                dsu.union(eq.charAt(0) - 97, eq.charAt(3) - 97);
        }
        for (String eq : equations) {
            if (eq.charAt(1) == '!')
                if (dsu.find(eq.charAt(0) - 97) == dsu.find(eq.charAt(3) - 97))
                    return false;
        }
        return true;
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
