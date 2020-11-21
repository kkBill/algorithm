package LeetCode;

public class _050_PowXN {
    /**
     * 这样写要考虑的边界情况太多了
     */
    /*
    public double myPow(double x, int n) {
        // 特判：任意值的0次幂等于1
        if(n == 0 || Math.abs(x - 1.0) < 0.0001) return 1.0;
        if(n == Integer.MIN_VALUE) return 0.0;

        boolean isNegative = n < 0;
        // 如果传入的n为-2147483648（也就是Integer.MIN_VALUE），那么直接转为-n就溢出了
        // 因为int型整数的最大值是 2147483647
        n = isNegative ? -n : n;
        double ans = x;
        int count = 1;
        int tmp = n/2;
        // System.out.println("tmp = " + tmp);
        while (count < tmp){
            ans = ans * ans;
            count *= 2;
            //System.out.println("ans = " + ans);
        }
        for(int i = count; i<n; i++){
            ans = ans * x;
        }
        //System.out.println("ans = " + ans);
        return isNegative ? 1 / ans : ans;
    }

    */

    public double myPow(double x, int n) {
        long N = n; // 防止溢出，这么处理非常关键
        if(N < 0){
            x = 1.0/x;
            N = -N;
        }
        double ans = 1.0;
        double currentProduct = x;
        for(long i = N; i > 0; i /= 2){
            if(i % 2 == 1){
                ans = ans * currentProduct;
            }
            currentProduct = currentProduct * currentProduct;
        }
        return ans;
    }

    public static void main(String[] args) {
        _050_PowXN obj = new _050_PowXN();
//        System.out.println(obj.myPow(2.0,10));
//        System.out.println(obj.myPow(2.1,3));
//        System.out.println(obj.myPow(-2.0,2));
//        System.out.println(obj.myPow(-2.0,-2));
//        System.out.println(obj.myPow(-2.0,3));
//        System.out.println(obj.myPow(-2.0,-3));
//
//        //特例
//        System.out.println(obj.myPow(1.0, Integer.MAX_VALUE));
//        System.out.println(obj.myPow(1.1, 0));

        System.out.println(obj.myPow(1.0001,12345));
        System.out.println(obj.myPow(1.0001,0));
        System.out.println(obj.myPow(2.0, Integer.MAX_VALUE));
        System.out.println(obj.myPow(2.0, Integer.MIN_VALUE)); // 1 /
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE % 2);
    }
}
