package LeetCode;

public class _263_UglyNumber {
    /**
     * 数学问题，判断一个数是否是丑数。
     * 什么是丑数？首先保证它是正整数，并且其质因子只包含2，3，5
     *
     * 1) 1 is typically treated as an ugly number.
     * 2) Input is within the 32-bit signed integer range: [−2^31,  2^31 − 1].
     *
     * 分析：
     * 1) 因为输入的数包含负数和0，而丑数的前提是正整数，因此先排除 num<=0 的情况
     * 2) 1 被认为是丑数
     * 3) 对于任意一个大于 1 的数，分别对其除以2，3，5，如果能够整除的，就继续判断num/2，num/3，num/5，直到最后变为1
     *    否则说明当前这个数不能被2或3或5整除，即它不是丑数
     */
    public boolean isUgly(int num) {
        if(num <= 0) return false;
        if(num == 1) return true;

        if(num % 2 == 0) return isUgly(num/2);
        if(num % 3 == 0) return isUgly(num/3);
        if(num % 5 == 0) return isUgly(num/5);

        return false;
    }

    public static void main(String[] args) {
        _263_UglyNumber obj = new _263_UglyNumber();
        System.out.println(obj.isUgly(6));
        System.out.println(obj.isUgly(8));
        System.out.println(obj.isUgly(14));
        System.out.println(obj.isUgly(0));
    }
}
