package random;

public class QuickSort {
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Quick sort with randomness
    //10, 7, 8, 9, 1, 5
    //1, 7, 8, 9, 10, 5
    //1, 5, 8, 9, 10, 7
    static int partition(int[] arr, int low, int high) {
        int pivotIdx = (int) (Math.random() * (high - low + 1)) + low;
        swap(arr, pivotIdx, high);
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static int quickSort(int[] arr, int low, int high, int expectedPos) {
        if (low < high) {
            int pi = partition(arr, low, high);
            if (expectedPos < pi)
                return quickSort(arr, low, pi - 1, expectedPos);
            else if (expectedPos > pi)
                return quickSort(arr, pi + 1, high, expectedPos);
            else if (pi == expectedPos)
                return arr[pi];
        }
        return arr[expectedPos];
    }

    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }
}
