package leetcode.veryCommon.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InsertDeleteGetRandom {
    class RandomizedCollection {
        ArrayList<Integer> data;
        HashMap<Integer, HashSet<Integer>> dataIdxMap;
        java.util.Random rand = new java.util.Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            dataIdxMap = new HashMap<>();
            data = new ArrayList<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            data.add(val);
            if (!dataIdxMap.containsKey(val)) {
                HashSet<Integer> tmpSet = new HashSet<>();
                tmpSet.add(data.size() - 1);
                dataIdxMap.put(val, tmpSet);
                return true;
            } else {
                dataIdxMap.get(val).add(data.size() - 1);
                return false;
            }
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (!dataIdxMap.containsKey(val))
                return false;
            else {
                int maxIdx = data.size() - 1;
                int lastVal = data.get(maxIdx);
                if (lastVal == val) {
                    dataIdxMap.get(val).remove(maxIdx);
                    data.remove(maxIdx);
                } else {
                    int delIdx = dataIdxMap.get(val).iterator().next();
                    dataIdxMap.get(val).remove(delIdx);
                    dataIdxMap.get(lastVal).remove(maxIdx);
                    dataIdxMap.get(lastVal).add(delIdx);
                    data.set(delIdx, lastVal);
                    data.remove(maxIdx);
                }
                if (dataIdxMap.get(val).size() == 0)
                    dataIdxMap.remove(val);
                return true;
            }
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            return data.get(rand.nextInt(data.size()));
        }
    }
}
