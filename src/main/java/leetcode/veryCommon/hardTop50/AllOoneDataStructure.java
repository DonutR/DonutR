package leetcode.veryCommon.hardTop50;

import java.util.HashMap;
import java.util.HashSet;

public class AllOoneDataStructure {
    class AllOne {

        HashMap<String, Integer> kCtMap;
        HashMap<Integer, HashSet<String>> ctKeyMap;
        int min, max;

        /**
         * Initialize your data structure here.
         */
        public AllOne() {
            min = max = 0;
            kCtMap = new HashMap<>();
            ctKeyMap = new HashMap<>();
        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            int prevKeyCt = kCtMap.getOrDefault(key, 0);
            int newKeyCt = prevKeyCt + 1;
            kCtMap.put(key, newKeyCt);

            if (ctKeyMap.containsKey(prevKeyCt))
                ctKeyMap.get(prevKeyCt).remove(key);
            ctKeyMap.computeIfAbsent(newKeyCt, x -> new HashSet<>()).add(key);
            if (newKeyCt > max)
                max = newKeyCt;
            if (newKeyCt < min || ctKeyMap.getOrDefault(prevKeyCt, new HashSet<>()).isEmpty())
                min = newKeyCt;
            if (ctKeyMap.getOrDefault(min, new HashSet<>()).isEmpty())
                min = max;
            System.out.println(min + " " + max);
        }


        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            int prevKeyCt = kCtMap.getOrDefault(key, 0);
            int newKeyCt = prevKeyCt - 1;
            if (newKeyCt <= 0)
                kCtMap.remove(key);
            else {
                kCtMap.put(key, newKeyCt);
                ctKeyMap.computeIfAbsent(newKeyCt, x -> new HashSet<>()).add(key);
            }
            if (ctKeyMap.containsKey(prevKeyCt))
                ctKeyMap.get(prevKeyCt).remove(key);
            if (prevKeyCt == max && ctKeyMap.getOrDefault(prevKeyCt, new HashSet<>()).isEmpty())
                max = newKeyCt;
            if (prevKeyCt == min && ctKeyMap.getOrDefault(prevKeyCt, new HashSet<>()).isEmpty())
                min = newKeyCt;
            if (ctKeyMap.getOrDefault(min, new HashSet<>()).isEmpty())
                min = kCtMap.entrySet().stream().map(i -> i.getValue()).min((a, b) -> a - b).orElseGet(() -> 0);
            System.out.println(min + " " + max);
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            HashSet<String> st = ctKeyMap.getOrDefault(max, new HashSet<>());
            return st.isEmpty() ? "" : st.iterator().next();
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            HashSet<String> st = ctKeyMap.getOrDefault(min, new HashSet<>());
            return st.isEmpty() ? "" : st.iterator().next();
        }
    }
}
