package leetcode.veryCommon.array;

import java.util.TreeMap;

public class SnapshotArray {
    class SnapshotArray1 {

        int length, snapId;
        int[] array;
        TreeMap<Integer, Integer>[] snap;

        public SnapshotArray1(int length) {
            snapId = 0;
            this.length = length;
            this.array = new int[length];
            snap = new TreeMap[length];
            for (int i = 0; i < length; i++)
                snap[i] = new TreeMap<>();
        }

        public void set(int index, int val) {
            array[index] = val;
        }

        public int snap() {
            for (int i = 0; i < length; i++) {
                if (snap[i].floorKey(snapId) == null)
                    snap[i].put(snapId, array[i]);
                else if (snap[i].floorEntry(snapId).getValue() != array[i])
                    snap[i].put(snapId, array[i]);
                else {
                    snap[i].remove(snap[i].floorKey(snapId));
                    snap[i].put(snapId, array[i]);
                }
            }
            return snapId++;
        }

        public int get(int index, int snap_id) {
            return snap[index].ceilingEntry(snap_id).getValue();
        }
    }


    class SnapshotArray2 {
        TreeMap<Integer, Integer>[] A;
        int snap_id = 0;

        public SnapshotArray2(int length) {
            A = new TreeMap[length];
            for (int i = 0; i < length; i++) {
                A[i] = new TreeMap<Integer, Integer>();
                A[i].put(0, 0);
            }
        }

        public void set(int index, int val) {
            A[index].put(snap_id, val);
        }

        public int snap() {
            return snap_id++;
        }

        public int get(int index, int snap_id) {
            return A[index].floorEntry(snap_id).getValue();
        }
    }
}
