package LeetCode;

public class _066_PlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 没有产生进位
        if(digits[n-1] + 1 < 10) {
            digits[n-1]++;
            return digits;
        }

        // 产生进位
        int carry = 1;
        digits[n-1] = 0;
        for(int i = n - 2; i >= 0; i--) {
            int d = digits[i] + carry;
            if(d < 10){
                digits[i] = d;
                carry = 0;
                break;
            }else {
                digits[i] = 0;
                carry = 1;
            }
        }
        if(carry == 1){
            int[] res = new int[n+1];
            res[0] = 1;
            return res;
        }else{
            return digits;
        }
    }

    public static void main(String[] args) {
        _066_PlusOne obj = new _066_PlusOne();
        int[] digits = new int[]{0};
        int[] res = obj.plusOne(digits);
        for(int d : res){
            System.out.print(d + " ");
        }
    }
}
