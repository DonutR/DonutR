package leetcode.veryCommon.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */
public class RandomizedSet {
    ArrayList<Integer> randomArr;
    HashMap<Integer, Integer> valueMap;
    Random rn = new Random();

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        randomArr = new ArrayList<>();
        valueMap = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!valueMap.containsKey(val)) {
            randomArr.add(val);
            valueMap.put(val, randomArr.size() - 1);
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (valueMap.containsKey(val)) {
            int delValLoc = valueMap.get(val);
            if (delValLoc < randomArr.size() - 1) {
                int lastVal = randomArr.get(randomArr.size() - 1);
                valueMap.put(lastVal, delValLoc);
                randomArr.set(delValLoc, lastVal);
            }
            valueMap.remove(val);
            randomArr.remove(randomArr.size() - 1);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        if (valueMap.size() == 0)
            return -1;
        return randomArr.get(rn.nextInt(randomArr.size()));
    }
}
