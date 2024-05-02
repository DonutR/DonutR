package leetcode.veryCommon.greedy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class JumpGameII {
    //[2,3,1,1,1,2,3,1,1,2,2,1,1,1]
    public int jump1(int[] nums) {
        if (nums.length < 2) return 0;
        int level = 0, currentMax = 0, i = 0, nextMax = 0;

        while (true) {
            level++;
            for (; i <= currentMax && i < nums.length; i++) {    //traverse current level , and update the max reach of next level
                nextMax = Math.max(nextMax, nums[i] + i);
                if (nextMax >= nums.length - 1)
                    return level;   // if last element is in level+1,  then the min jump=level
            }
            if (i >= nums.length)   //nodes count of current level>0
                return 0;
            currentMax = nextMax;
        }
    }

    /*
    I try to change this problem to a BFS problem, where nodes in level i are all the nodes that can be reached in i-1th jump. for example. 2 3 1 1 4 , is
    2||
    3 1||
    1 4 ||
    clearly, the minimum jump of 4 is 2 since 4 is in level 3. my ac code.
     */
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int level = 0, currentMax = 0, i = 0, nextMax = 0;

        while (currentMax - i + 1 > 0) {        //nodes count of current level>0
            level++;
            for (; i <= currentMax; i++) {    //traverse current level , and update the max reach of next level
                nextMax = Math.max(nextMax, nums[i] + i);
                if (nextMax >= nums.length - 1)
                    return level;   // if last element is in level+1,  then the min jump=level
            }
            currentMax = nextMax;
        }
        return 0;

    }
}