package lambdaMethodRefStream;

import java.util.AbstractMap;

//https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.SimpleEntry.html
public class SimpleEntryEx {
    public static void main(String[] args) {
        AbstractMap.SimpleEntry<Integer, Integer> es = new AbstractMap.SimpleEntry<>(1, 2);
        System.out.println(es.getKey());
        System.out.println(es.getValue());
    }
}
