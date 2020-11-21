package LeetCode;

public class _009_PalindromeNumber {
    /*
    // 方法1：反转整个整数，要考虑溢出的情况
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int a = Integer.MAX_VALUE / 10;
        int reverseX = 0, originalX = x;
        while(x != 0) {
            int pop = x % 10;
            x /= 10;
            if(reverseX > a || (reverseX == a && pop > 7)) return false; // 溢出
            reverseX = reverseX * 10 + pop;
        }
        return reverseX == originalX;
    }

     */

    // 翻转“半个”整数，无需考虑溢出的情况  --> 太骚了
    public boolean isPalindrome(int x) {
        // 如果是负数，或者 x 的末位为 0，则直接可以判定不是回文数
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int reversedX = 0;
        while (x > reversedX) {
            int pop = x % 10;
            x /= 10;
            reversedX = reversedX * 10 + pop;
        }
        // 比如原x = 1221，则循环结束后，x = 12, reversedX = 12
        // 比如原x = 121，则循环结束后，x = 1, reversedX = 12
        return x == reversedX || x == reversedX/10;
    }


    public static void main(String[] args) {
        _009_PalindromeNumber obj = new _009_PalindromeNumber();
        System.out.println(obj.isPalindrome(121));
        System.out.println(obj.isPalindrome(10));
    }
}
