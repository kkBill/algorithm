package LeetCode;

public class _029_DivideTwoIntegers {
    /**
     * 把所有的数都转化为正数来处理，符号最后再添加
     */
    /*
    public int divide(int dividend, int divisor) {
        // 特判：除数为0。（题目已经保证被除数不为0，故无需考虑）
        if(dividend == 0) return 0;

        // 特判：如果被除数是1（或-1），直接返回除数本身（除数的相反数）
        //if(divisor == 1) return dividend;
        //else if(divisor == -1) return opposite(dividend);

        // 但是这种情况有一个特例，即 MIN_VALUE / -1 ，由于对 int 型的最小值取相反数会发生溢出，
        // 根据题意，发生溢出时返回 MAX_VALUE。因此上面的写法修改如下：
        if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
        if(divisor == 1) return dividend;
        else if(divisor == -1) return opposite(dividend);


        // 调整，如果出现负数，将其转化为正数处理，最后返回结果时再处理符号
        boolean isPositive = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
        if(dividend < 0)
            dividend = opposite(dividend);
        if(divisor < 0)
            divisor = opposite(divisor);

        // 这里又会出现一个意外，比如 dividend = Integer.MIN_VALUE, divisor = 2,
        // 理应返回 -1073741824，但是根据这里的逻辑，会返回 0
        // 对于 Integer.MIN_VALUE，也就是 -2147483648，和其他数值不一样的是：
        // opposite(-2147483648) 的结果仍然等于 -2147483648，而不是正常逻辑下的 2147483648，这是由于溢出造成的
        // 这个问题在这一版代码中似乎无法解决

        // 特判：除数的绝对值 小于 被除数的绝对值，结果为0。比如 1/3, -1/3, 1/(-3)
        if(dividend < divisor) return 0;

        int originalDividend = dividend, originalDivisor = divisor;
        // 二分法
        int ans = 1;
        dividend -= divisor;
        while (divisor <= dividend){
            ans = ans + ans;
            divisor += divisor;
            dividend -= divisor;
        }
        // 剩余的部分线性处理
        while (originalDividend - divisor >= originalDivisor){
            ans++;
            divisor += originalDivisor;
        }

        return isPositive ? ans : opposite(ans);
    }
    */

    /**
     * 将所有的数都转化成负数来处理
     */
public int divide(int dividend, int divisor) {
    // 特判：除数为0。（题目已经保证被除数不为0，故无需考虑）
    if(dividend == 0) return 0;
    //
    if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
    if(divisor == 1) return dividend;
    else if(divisor == -1) return opposite(dividend);

    // 修改1
    // 调整，如果出现正数，将其转化为负数处理，最后返回结果时再处理符号
    boolean isPositive = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
    if(dividend > 0)
        dividend = opposite(dividend);
    if(divisor > 0)
        divisor = opposite(divisor);

    // 修改2
    // 特判：除数的绝对值 小于 被除数的绝对值，结果为0。比如 1/3, -1/3, 1/(-3)
    if(dividend > divisor) return 0;

    int originalDividend = dividend, originalDivisor = divisor;
    // 二分法
    int ans = 1;
    dividend -= divisor;
    while (divisor >= dividend){ // 修改3
        ans = ans + ans;
        divisor += divisor;
        dividend -= divisor;
    }
    // 剩余的部分线性处理
    while (originalDividend - divisor <= originalDivisor){ // 修改4
        ans++;
        divisor += originalDivisor;
    }

    return isPositive ? ans : opposite(ans);

}

private int opposite(int x){
    return ~x + 1;
}

    public static void main(String[] args) {
        _029_DivideTwoIntegers obj = new _029_DivideTwoIntegers();

        System.out.println(obj.divide(10,3));
        System.out.println(obj.divide(10,-3));
        System.out.println(obj.divide(-10,3));
        System.out.println(obj.divide(-10,-3));
        System.out.println(obj.divide(9,3));

        System.out.println(obj.divide(0,3));
        System.out.println(obj.divide(1,3));
        System.out.println(obj.divide(1,-3));
        System.out.println(obj.divide(Integer.MAX_VALUE,1));
        System.out.println(obj.divide(Integer.MIN_VALUE,2));

        // 几个自己没想到的case
        // case1: Integer.MIN_VALUE / -1 应该等于 Integer.MAX_VALUE

        // case2: Integer.MIN_VALUE / 2 = -1073741824
    }
}
