package leetcode.veryCommon.binarySearch;

public class Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        int m = matrix.length, n = matrix[0].length, start = 0, end = m * n - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            System.out.println(mid);
            if (matrix[mid / n][mid % n] == target)
                return true;
            else if (matrix[mid / n][mid % n] < target)
                start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }
}
