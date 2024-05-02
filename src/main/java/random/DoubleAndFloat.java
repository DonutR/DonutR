package random;

import java.util.*;

public class DoubleAndFloat {
    //https://www.youtube.com/watch?v=PZRI1IfStY0
    //Why double and float aggregation create random sums?
    //It's because of change in ordering.
    //If you order the double value and then sum it multiple times, It will give same sum.

    public static void main(String[] args) {
        List<Double> dArray = new ArrayList<>();
        double sum = 0.0;
        for (int i = 0; i < 10000000; i++) {
            double num = Math.random();
            sum += num;
            dArray.add(num);
        }
        System.out.println("In LOOP SUM  =" + sum);

        System.out.println("Array SUM  1 =" + dArray.stream().reduce(0.0, (a, b) -> a + b));

        Collections.shuffle(dArray);

        System.out.println("Random Array SUM  2 =" + dArray.stream().reduce(0.0, (a, b) -> a + b));

        Collections.shuffle(dArray);

        System.out.println("Random Array SUM  3 =" + dArray.stream().reduce(0.0, (a, b) -> a + b));

        Collections.shuffle(dArray);

        System.out.println("Random Array SUM  4 =" + dArray.stream().reduce(0.0, (a, b) -> a + b));

        Collections.sort(dArray);
        System.out.println("Sort Array SUM  1 =" + dArray.stream().reduce(0.0, (a, b) -> a + b));
        System.out.println("Sort Array SUM  2 =" + dArray.stream().reduce(0.0, (a, b) -> a + b));

    }

    public boolean isReflected(int[][] points) {
        HashSet<String> pointSet = new HashSet<>();
        Arrays.stream(points).forEach(p -> pointSet.add(p[0] + ":" + p[1]));

        int maxX = Arrays.stream(points).map(i -> i[0]).max(Comparator.naturalOrder()).get();
        int minX = Arrays.stream(points).map(i -> i[0]).min(Comparator.naturalOrder()).get();
        float midX = (maxX + minX) / (float) 2;

        for (int[] p : points) {
            int mirrorX = (int) (midX + midX) - p[0];
            if (!pointSet.contains(mirrorX + ":" + p[1])) return false;
        }

        return true;
    }
}
