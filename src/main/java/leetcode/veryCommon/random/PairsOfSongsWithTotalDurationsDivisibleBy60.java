package leetcode.veryCommon.random;

import scala.Int;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 * <p>
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 * <p>
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public static void main(String[] args) {
        int[] num = {60, 60, 60, 30, 20, 150, 100, 40};
        System.out.println(numPairsDivisibleBy601(num));
    }

    public static int numPairsDivisibleBy601(int[] time) {
        HashMap<Integer, Integer> ctMap = new HashMap<>();
        Arrays.stream(time).forEach(a -> {
            if (!ctMap.containsKey(a))
                ctMap.put(a, 1);
            else {
                int ct = ctMap.get(a);
                ctMap.put(a, ct + 1);
            }
        });
        int out = 0;
        for (int i = 0; i < time.length; i++) {
            int reqRem = 60 - time[i] % 60;
            if (ctMap.containsKey(reqRem) && ctMap.get(reqRem) > 0) {
                ctMap.put(reqRem, ctMap.get(reqRem) - 1);
                if (reqRem != time[i]) ctMap.put(time[i], ctMap.get(time[i]) - 1);
                out++;
            }
        }
        return out;
    }

    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int cntr = 0;
        for (int t : time) {
            cntr += map.getOrDefault((60 - t % 60) % 60, 0);
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }
        return cntr;
    }
}
