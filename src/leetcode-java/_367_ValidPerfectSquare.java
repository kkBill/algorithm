package LeetCode;

public class _367_ValidPerfectSquare {
    /*
    public boolean isPerfectSquare(int num) {
        //if(num == 1) return true;
        double left = 1.0, mid = 0.0, right = num, diff = 0.0000001;
        while (right - left > diff) {
            mid = (right - left) / 2 + left;
            double t = mid * mid;
            //System.out.println("t = " + t);
            if (t - num > 0.0) right = mid;
            else if(t - num < 0.0) left = mid;
            else break;
        }
        int temp = (int)Math.round(mid);
        //System.out.println("left = " + mid + ",temp = " + temp);
        return Math.abs(temp - mid) < diff;
    }
    */

    /**
     * 二分法
     */
    public boolean isPerfectSquare(int num) {
        // 使用 long 是为了在 mid * mid 时避免溢出
        long left = 1, right = num, mid, tempSquare;
        while (left <= right) {
            mid = (right - left)/2 + left;
            tempSquare = mid * mid;
            if(tempSquare > num) right = mid - 1;
            else if(tempSquare < num) left = mid + 1;
            else return true;
        }
        return false;
    }

    /**
     * 牛顿迭代法
     * 时间复杂度：O(log n)(二次收敛？)
     */
    /*
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        long x = num / 2; // 写成 int x = num / 2; 会出错
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }
    */


    public static void main(String[] args) {
        _367_ValidPerfectSquare obj = new _367_ValidPerfectSquare();
        System.out.println(obj.isPerfectSquare(15));
        System.out.println(obj.isPerfectSquare(16));
        System.out.println(obj.isPerfectSquare(1));
    }
}
