package LeetCode;

public class _069_SqrtX {
    /**
     * 方法1：二分法
     */
    /*
    // 如果要求返回精确值
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        double left = 1.0, mid = 0.0, right = x, diff = 0.0000001;
        while (right - left > diff) {
            mid = (right - left) / 2 + left;
            double t = mid * mid;
            //System.out.println("t = " + t);
            if (t - x > 0.0) right = mid;
            else if(t - x < 0.0) left = mid;
            else break;
        }


        // 在java中的Math类中有三种方法对浮点数取整
        // floor  //向下取整
        // ceil   //向上取整
        // round  //四舍五入取整
        //
        // 例如：
        // Math.floor(1.3); // 1
        // Math.floor(1.7); // 1
        // Math.ceil(1.3);  // 2
        // Math.ceil(1.7);  // 2
        // Math.round(1.3); // 1
        // Math.round(1.7); // 2

        int temp = (int)Math.round(mid);
        //System.out.println("left = " + mid + ",temp = " + temp);
        return Math.abs(temp - mid) < diff ? temp : (int)mid;
    }
    */
    public int mySqrt(int x) {
        if (x == 0) return x;
        long tempSquare;
        int left = 1, right = x, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            tempSquare = (long)mid * mid; // 注意：写成 tempSquare = mid*mid; 会溢出
            System.out.println(left + " " + right + " " + mid + " " + tempSquare);
            if (tempSquare > x) right = mid-1;
            else if (tempSquare < x) left = mid+1;
            else return mid;
        }
        return right;
    }


    /**
     * 方法2：牛顿法
     */
    /*
    // 如果要求返回尽量精确的值
    public double mySqrt(int x) {
        if(x == 0) return 0;
        double x0 = x;
        double x1 = (x0 + x/x0)/2;
        // 1e-6 是科学计数法，表示 1*10^(-6)，也就是 0.000001
        // 用来抵消浮点运算中因为误差造成的相等无法判断的情况，它通常是一个非常小的数字，
        // 具体多小要根据要求的精度来设置
        double precision = 1e-6;
        while (Math.abs(x1 - x0) > precision){
            x0 = x1;
            x1 = (x0 + x/x0)/2;
        }
        return x1;
    }

    // 本题中只要求精确到整数部分
    public int mySqrt(int x) {
        if(x == 0) return 0;
        double x0 = x;
        double x1 = (x0 + x/x0)/2;
        while (Math.abs(x1 - x0) >= 1){
            x0 = x1;
            x1 = (x0 + x/x0)/2;
        }
        return (int)x1;
    }
    */

    public static void main(String[] args) {
        _069_SqrtX obj = new _069_SqrtX();

        System.out.println(obj.mySqrt(2147395599));
//        System.out.println(obj.mySqrt(1));
//        System.out.println(obj.mySqrt(2));
//        System.out.println(obj.mySqrt(3));
//        System.out.println(obj.mySqrt(4));
//        System.out.println(obj.mySqrt(8));
//        System.out.println(obj.mySqrt(9));
//        System.out.println(obj.mySqrt(16));
//        System.out.println(obj.mySqrt(25));

    }
}
