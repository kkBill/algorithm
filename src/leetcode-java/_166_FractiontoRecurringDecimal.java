package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _166_FractiontoRecurringDecimal {
    /**
     * 先考虑边界情况，由于本题中没有限定说必须使用32位int型数
     * 因此对于可能出现溢出的情况，可以转化成long类型
     * 0 ÷ 1 = 0
     * 1 ÷ 0 = 不合法
     * 2147483647 ÷ 1 = 2147483647
     * 2147483647 ÷ -1 = -2147483647
     * -2147483648 ÷ -1 = 溢出（根据经验应该返回 2147483647）
     * -2147483648 ÷ -2 =
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        // 确定符号
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
            result.append('-');
        }

        // 边界情况
        if (numerator == 0) return "0";
        if (denominator == 0) return "error";
        if (numerator == Integer.MIN_VALUE && denominator == -1) return "2147483647";

        long n = Math.abs((long) numerator);
        long d = Math.abs((long) denominator);
        // 整数部分可以直接计算得到
        result.append(n / d);
        long remainder = n % d;// 从余数部分开始处理
        if(remainder == 0) return result.toString(); // 整除

        result.append('.');
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0){
            if(map.containsKey(remainder)){
                result.insert(map.get(remainder),"(");
                result.append(')');
                break;
            }
            map.put(remainder,result.length());
            remainder *= 10;
            result.append(remainder / d);
            remainder %= d;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        _166_FractiontoRecurringDecimal obj = new _166_FractiontoRecurringDecimal();
        System.out.println(obj.fractionToDecimal(1,2));
        System.out.println(obj.fractionToDecimal(5,4));
        System.out.println(obj.fractionToDecimal(2,1));
        System.out.println(obj.fractionToDecimal(1,125));
        System.out.println(obj.fractionToDecimal(1,3));
    }
}
