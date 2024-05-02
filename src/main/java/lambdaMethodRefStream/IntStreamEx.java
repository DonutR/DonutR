package lambdaMethodRefStream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class IntStreamEx {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int sum = IntStream.of(A).sum();
        Arrays.stream(A).boxed().mapToInt(a -> a).sum();

        String[] AS = {"1", "2", "3", "4", "5"};
        Arrays.stream(AS).mapToInt(a -> Integer.parseInt(a)).sum();

        IntStream stream = IntStream.range(0, 10);
        stream.forEach(a -> {
            System.out.println(a);
        });

        System.out.println("The sum of all elements in the array is " + sum);


    }
}
