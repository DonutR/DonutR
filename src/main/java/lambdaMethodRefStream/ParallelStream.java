package lambdaMethodRefStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
//To use this check for following conditions:
        //1) See if the problem on hand is actually parallelizable.
        //2) If you are ok to spend resource to get result fast.
        //3) Data size is good for better performance.
        //4) Task computation is big enough.


        //Example of Filter, Map, Reduce and Specialized Reduce

        List<String> tempList = new ArrayList<String>();
        tempList.add("Renso");
        System.out.println(
                tempList.stream()
                        .map(Integer::parseInt)
                        .filter(e -> e % 2 == 0)
                        .reduce(0, Integer::sum));


        System.out.println(
                tempList.stream()
                        .map(Integer::parseInt)
                        .filter(e -> e % 2 == 0)
                        .mapToInt(e -> e * 2).sum());


        //AVOID SHARED MUTABILITY
        //Lambda to double even values and put to a leetcode.commonList.list

        List<Integer> resultList = new ArrayList<>();
        tempList.stream()
                .map(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .forEach(e -> resultList.add(e));

        System.out.println(resultList);
        //This is wrong because if you use parallel
        // stream on this case then it can cause the leetcode.commonList.list to behave wrongly

        List<Integer> resultList2 = tempList.stream()
                .map(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2).collect(Collectors.toList());

        //Collector.toMap Example
        //Create a Map which has name+gender as Key and Person object as value
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("a", "b", 10));
        personList.add(new Person("b", "b", 10));
        personList.add(new Person("c", "b", 10));
        personList.add(new Person("d", "b", 10));

        personList
                .stream()
                .collect(
                        Collectors.toMap(
                                person -> person + "-" + person.gender,
                                peron -> peron
                        ));
        //Collector.groupingBy Example
        //Create a Map where Key is person name and value is a List of Person object with that name
        personList
                .stream()
                .collect(Collectors.groupingBy(person -> person.name));


        personList
                .stream()
                .collect(Collectors.groupingBy(Person::getName));

        //Collector.groupingBy + mapping Example
        //Perform a Map operation on the grouping values before aggregating it.
        //Create a Map where Key is person name and value is a List of Ages Integer with that name
        personList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getName,
                                Collectors.mapping(
                                        Person::getAge,
                                        Collectors.toList()
                                )
                        )
                );

        /*
        Characteristics of streams:
        	Sized :
        	Ordered
        	Distinct
        	Sorted
        */

        tempList.stream()
                .map(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .sorted()
                .distinct()
                .reduce(0, Integer::sum);

        //Infinite streams
        Stream
                .iterate(0,e -> e+1)
                .filter(e-> e%2==0)
                .filter(e-> Math.sqrt(e) > 20)
                .mapToInt(e-> e*2)
                .limit(10)
                .sum();
    }

    public static class Person {
        public String name;
        public String gender;
        public Integer age;

        public Person(String name, String gender, Integer age) {
            this.name = name;
            this.gender = gender;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public Integer getAge() {
            return age;
        }
    }
}
