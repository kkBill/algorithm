package LeetCode;

public class _415_AddStrings {
    public String addStrings(String num1, String num2) {
        int carry = 0, i = num1.length()-1, j = num2.length()-1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0){
            int x1 = i >= 0 ? num1.charAt(i)-'0' : 0;
            int x2 = j >= 0 ? num2.charAt(j)-'0' : 0;
            int d = x1 + x2 + carry;
            if(d >= 10){
                carry = 1;
                d %= 10;
            }else{
                carry = 0;
            }
            sb.append(d);
            i--;
            j--;
        }
        if(carry == 1) sb.append(1);
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        _415_AddStrings obj = new _415_AddStrings();
        System.out.println(obj.addStrings("129","111"));
        System.out.println(obj.addStrings("","111"));
    }
}
