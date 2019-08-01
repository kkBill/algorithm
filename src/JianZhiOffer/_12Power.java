package JianZhiOffer;

public class _12Power {
    /*
    public double PowerWithUnsignedExponent(double base, int exp) {
        double res = 1.0;
        for(int i=0; i<exp; i++){
            res *= base;
        }
        return res;
    }
    */

    // 利用公式求a的n次方，速度更快
    public double PowerWithUnsignedExponent(double base, int exp) {
        if(exp == 0) return 1.0;
        if(exp == 1) return base;
        double res = PowerWithUnsignedExponent(base, exp>>1);
        res *= res;
        if(exp % 2 == 1)
            res *= base;
        return res;
    }

    // 判断两个浮点数是否相等，不能简单的使用 ==
    public boolean equal(double a, double b) {
        if(Math.abs(a-b) <= 0.000001) return true;
        else return false;
    }

    public double Power(double base, int exponent) {
        if(exponent >= 0)
            return PowerWithUnsignedExponent(base, exponent);
        else{
            if(equal(base, 0.0)){
                throw new RuntimeException("Invalid input!");
            }else{
                return 1.0 / PowerWithUnsignedExponent(base, -exponent);
            }
        }
    }
}
