package LeetCode;

public class _326_PowerofThree {

    /*
    // 根据定义来写
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        while (n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }
    */

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        _326_PowerofThree obj = new _326_PowerofThree();
        System.out.println(obj.isPowerOfThree(3));
        System.out.println(obj.isPowerOfThree(6));
        System.out.println(obj.isPowerOfThree(27));

        System.out.println(Math.log10(Integer.MAX_VALUE)/Math.log10(3));
        System.out.println((int)Math.pow(3,19));
    }
}
