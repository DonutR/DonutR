package leetcode.veryCommon.hardTop50;

public class ReachingPoints {
    //Think of moving from target -> source 1 by 1
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx < sx || ty < sy || tx == 0 || ty == 0) {
            if (tx >= ty)
                tx = tx - ty;
            else ty = ty - tx;
            System.out.println(tx + " " + ty);
            if (tx == sx && ty == sy)
                return true;
        }
        return false;
    }

    //Think of moving from target -> source exponentially
    public static boolean reachingPoints2(int sx, int sy, int tx, int ty) {

        while (sx < tx && sy < ty)
            if (tx < ty)
                ty %= tx;
            else
                tx %= ty;

        if (sx == tx && sy <= ty && (ty - sy) % sx == 0)
            return true;

        return sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }

}
