package leetcode.veryCommon.twoPointer;

public class ReverseString {
    public void reverseString(char[] s) {
        int l=s.length;
        for (int i = 0, j =  l- 1; i < l / 2; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }
}
