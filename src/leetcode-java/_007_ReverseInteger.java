package LeetCode;

public class _007_ReverseInteger {
    public int reverse(int x) {
        int a = Integer.MAX_VALUE / 10, b = Integer.MIN_VALUE / 10;
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x /= 10;
            if(rev > a || (rev == a && pop > 7)) return 0;
            if(rev < b || (rev == b && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        _007_ReverseInteger obj = new _007_ReverseInteger();
        System.out.println(obj.reverse(0));
        System.out.println(obj.reverse(120));
        System.out.println(obj.reverse(-123));
        System.out.println(obj.reverse(Integer.MAX_VALUE));
        System.out.println(obj.reverse(Integer.MIN_VALUE));
        System.out.println(obj.reverse(Integer.MAX_VALUE - 1));
        System.out.println(obj.reverse(Integer.MIN_VALUE + 3));
        System.out.println(obj.reverse(1534236469)); // 0
//        System.out.println(9 * 1000000000); // 溢出，变成 410065408
//        System.out.println(-18 % 10); // -8
    }
}
