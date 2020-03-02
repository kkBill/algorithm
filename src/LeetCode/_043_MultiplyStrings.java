package LeetCode;

public class _043_MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        StringBuilder result = new StringBuilder();
        StringBuilder tailingZero = new StringBuilder();

        for (int i = num1.length() - 1; i >= 0; i--) {
            int carry = 0;
            int x1 = num1.charAt(i) - '0';
            StringBuilder product = new StringBuilder();
            product.append(tailingZero);

            for (int j = num2.length() - 1; j >= 0; j--) {
                int x2 = num2.charAt(j) - '0';
                int d = x1 * x2 + carry;
                if (d >= 10) {
                    carry = d / 10;
                    d = d % 10;
                } else {
                    carry = 0;
                }
                product.append(d);
            }
            if(carry > 0){
                product.append(carry);
            }

            result = addStrings(result.toString(), product.reverse().toString());
            tailingZero.append('0');
        }

        return result.toString();

    }

    public StringBuilder addStrings(String num1, String num2) {
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int x1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int x2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int d = x1 + x2 + carry;
            if (d >= 10) {
                carry = 1;
                d %= 10;
            } else {
                carry = 0;
            }
            sb.append(d);
            i--;
            j--;
        }
        if (carry == 1) sb.append(1);
        return sb.reverse();
    }

    public static void main(String[] args) {
        _043_MultiplyStrings obj = new _043_MultiplyStrings();
        System.out.println(obj.multiply("123", "456"));
    }
}
