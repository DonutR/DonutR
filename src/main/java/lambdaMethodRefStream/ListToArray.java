package lambdaMethodRefStream;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        ArrayList<Integer> out = new ArrayList<>();
        out.stream().mapToInt(a -> a).toArray();

        List<int[]> listOfArr = new ArrayList<>();
        listOfArr.stream().map(a -> a).toArray(int[][]::new);
        //return int[][] 2D array from List<int[]>

    }
}
