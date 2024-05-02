package lambdaMethodRefStream;

import java.util.*;
import java.util.stream.Collectors;

public class MethodRef {
    public static void main(String[] args) {

        List<String> tempList = new ArrayList<String>();
        tempList.add("Renso");
        tempList.forEach(System.out::println);
        tempList.forEach(e -> System.out.println(e));
        tempList
                .stream()
                .map(String::valueOf)
                .forEach(System.out::println);

        tempList.stream()
                .map(e -> e.toUpperCase())
                .forEach(System.out::println);
        tempList.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println(
                tempList
                        .stream()
                        .reduce("", (a, b) -> a.concat(b)));

        System.out.println(
                tempList
                        .stream()
                        .reduce("", String::concat));

        System.out.println(
                tempList
                        .stream()
                        .map(Integer::parseInt)
                        .reduce(0, (a, b) -> Integer.sum(a, b)));

        System.out.println(
                tempList
                        .stream()
                        .map(Integer::parseInt)
                        .reduce(0, Integer::sum));
        //Given a List of string do a lambda code to double the even number only and get the sum of entire leetcode.commonList.list
        System.out.println(
                tempList.stream()
                        .map(Integer::parseInt)
                        .map(e -> e % 2 == 0 ? e * 2 : e)
                        .reduce(0, Integer::sum));

        //Given a List of string do a lambda code to double the even and get the sum.
        System.out.println(
                tempList.stream()
                        .map(Integer::parseInt)
                        .filter(e -> e % 2 == 0)
                        .map(e -> e * 2)
                        .reduce(0, Integer::sum));

        System.out.println(
                tempList.stream()
                        .map(Integer::parseInt)
                        .filter(e -> e % 2 == 0)
                        .mapToInt(e -> e * 2).sum());

        //Sort an array 1D/2D
        int[][] courses2D = {{2, 1000}, {3, 900}, {0, 700}};
        Arrays.sort(courses2D, Comparator.comparingInt(a -> a[1]));
        Arrays.stream(courses2D).forEach(a -> System.out.println(a[0]));

        Integer[] courses1D = new Integer[10];
        Arrays.sort(courses1D, Comparator.comparing(a -> a, (a, b) -> a - b));

        int[] courses1D2 = {10, 6, 9, 3, 5, 8, 1, 0, 11};
        courses1D2 = Arrays.stream(courses1D2)
                .boxed()
                .sorted((a, b) -> b - a)
                .mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < courses1D2.length; i++)
            System.out.println(courses1D2[i]);


        HashMap<Integer, Integer> sortMap = new HashMap<>();
        sortMap.put(1, 2);
        sortMap.put(10, 3);
        sortMap.put(100, 4);
        sortMap.put(1000, 5);
        for (Map.Entry<Integer, Integer> es : sortMap.entrySet()) {
            System.out.println(es.getKey());
        }
        List<Map.Entry<Integer, Integer>> out = sortMap.entrySet().stream().sorted((a, b) -> a.getValue() - b.getValue()).collect(Collectors.toList());

        System.out.println("===");

        for (Map.Entry<Integer, Integer> a : out) {
            System.out.println(a.getKey());
        }
    }
}
