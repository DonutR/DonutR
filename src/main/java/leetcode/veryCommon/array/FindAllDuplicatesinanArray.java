package leetcode.veryCommon.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindAllDuplicatesinanArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int tmpNum = -1;
            if (nums[i] != i + 1) {
                tmpNum = nums[i];
                nums[i] = -1;
            }
            while (tmpNum > 0 && tmpNum != nums[tmpNum - 1]) {
                int tmpNum2 = tmpNum;
                tmpNum = nums[tmpNum2 - 1];
                nums[tmpNum2 - 1] = tmpNum2;
            }
            if (tmpNum > 0 && tmpNum == nums[tmpNum - 1])
                out.add(tmpNum);
        }
        System.out.println(out);
        return out;
    }

    public List<Integer> findDuplicates2(int[] nums) {
        HashSet<Integer> out = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i] && nums[nums[i] - 1] != nums[i]) {
                while (nums[nums[i] - 1] != nums[i]) {
                    int tmp = nums[i];
                    nums[i] = nums[tmp - 1];
                    nums[tmp - 1] = tmp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                out.add(nums[i]);
        }
        return new ArrayList<>(out);
    }
}
