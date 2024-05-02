package leetcode.veryCommon.binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindKClosestElements {
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int start = 0, end = arr.length - 1, i = -1, j = -1;
        int mid = 0, ct = 1;
        List<Integer> outList = new ArrayList<>();
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == x) {
                i = mid - 1;
                j = mid + 1;
                outList.add(x);
                ct++;
                break;
            } else if (arr[mid] < x)
                start = mid + 1;
            else end = mid - 1;
        }
        if (ct == 1) {
            i = start - 1;
            j = start;
        }
        while (ct <= k) {
            if (i >= 0 && j < arr.length) {
                if (x - arr[i] > arr[j] - x) {
                    outList.add(arr[j]);
                    j++;
                } else {
                    outList.add(arr[i]);
                    i--;
                }
            } else if (i >= 0) {
                outList.add(arr[i]);
                i++;
            } else if (j < arr.length) {
                outList.add(arr[j]);
                j++;
            }
            ct++;
        }
        Collections.sort(outList);
        return outList;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int start = 0, end = arr.length - 1, mid = 0;
        boolean numFound = false;
        List<Integer> outList = new ArrayList<>();
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == x) {
                numFound = true;
                break;
            } else if (arr[mid] < x)
                start = mid + 1;
            else end = mid - 1;
        }
        if (numFound) outList.add(arr[mid]);

        int l = numFound ? mid - 1 : start - 1,
                r = numFound ? mid + 1 : start,
                ct = numFound ? 1 : 0;

        while (ct < k) {
            if (l >= 0 && r < arr.length) {
                if (Math.abs(arr[l] - x) > Math.abs(arr[r] - x))
                    outList.add(arr[r++]);
                else
                    outList.add(arr[l--]);
            } else if (l >= 0)
                outList.add(arr[l--]);
            else if (r < arr.length)
                outList.add(arr[r++]);
            ct++;
        }
        Collections.sort(outList);
        return outList;
    }
}
