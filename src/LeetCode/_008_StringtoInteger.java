package LeetCode;

public class _008_StringtoInteger {

    public int myAtoi(String str) {
        int result = 0, sign = 1, t = Integer.MAX_VALUE / 10; // t 用于判别是否溢出
        boolean noSignBefore = true, noDigitBefore = true;
        for (char c : str.toCharArray()) {
            if (c == ' ' && noSignBefore && noDigitBefore) {
                continue;
            }
            if (c == '+' && noSignBefore && noDigitBefore) {
                noSignBefore = false;
                continue;
            }
            if (c == '-' && noSignBefore && noDigitBefore) {
                noSignBefore = false;
                sign = -1;
                continue;
            }
            if (Character.isDigit(c)) {
                noDigitBefore = false;
                int currDigit = c - '0';
                // 处理溢出
                if (sign == 1 && (result > t || (result == t && currDigit > 7))) return Integer.MAX_VALUE;
                if (sign == -1 && (result > t || (result == t && currDigit > 8))) return Integer.MIN_VALUE;

                result = result * 10 + currDigit;
                continue;
            }

            // 遇到无效字符，直接跳出循环
            break;
        }
        return sign * result;
    }

    public static void main(String[] args) {
        _008_StringtoInteger obj = new _008_StringtoInteger();
        System.out.println(obj.myAtoi("   +-43")); // 0
        System.out.println(obj.myAtoi("   --43")); // 0
        System.out.println(obj.myAtoi("   +0 123")); // 0
        System.out.println(obj.myAtoi("0-1")); // 0
        System.out.println(obj.myAtoi(" -   1")); // 0

        System.out.println(obj.myAtoi("   -43")); // -43
        System.out.println(obj.myAtoi("   00043")); // 43
        System.out.println(obj.myAtoi("   -00043")); // 43
        System.out.println(obj.myAtoi("   -439-5 with words")); // -439
        System.out.println(obj.myAtoi("   -439+5 with words")); // -439
        System.out.println(obj.myAtoi("   439   5 ")); // 439
        System.out.println(obj.myAtoi("4395 with words")); // 4395
        System.out.println(obj.myAtoi("words and 123")); // 0
        System.out.println(obj.myAtoi("2147483647"));   // 2147483647
        System.out.println(obj.myAtoi("2147483648"));   // 正向溢出
        System.out.println(obj.myAtoi("-2147483648"));   // -2147483648
        System.out.println(obj.myAtoi("-2147483649"));   // 负向溢出
        System.out.println(obj.myAtoi("-91283472332")); // 负向溢出
    }
}
