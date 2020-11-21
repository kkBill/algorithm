package LeetCode;

public class _231_PowerofTwo {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        return ((n - 1) & n) == 0;
    }

    public static void main(String[] args) {
        _231_PowerofTwo obj = new _231_PowerofTwo();
        System.out.println(obj.isPowerOfTwo(0));

        System.out.println(obj.isPowerOfTwo(1));
        System.out.println(obj.isPowerOfTwo(16));
        System.out.println(obj.isPowerOfTwo(3));
    }
}
