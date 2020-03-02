package LeetCode;

public class _067_AddBinary {
/**
 * 时间复杂度：O(max(m,n))
 * 空间复杂度：O(max(m,n))
 */
public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int i = a.length()-1, j = b.length()-1;
    while(i >= 0 || j >= 0){
        int xi = i >= 0 ? a.charAt(i)-'0' : 0;
        int xj = j >= 0 ? b.charAt(j)-'0' : 0;
        int d = carry + xi + xj;
        if(d > 1) {
            carry = 1;
            d = d % 2;
        }else {
            carry = 0;
        }
        sb.append((char)('0' + d));
        i--;
        j--;
    }
    if(carry == 1){
        sb.append('1');
    }
    return sb.reverse().toString();
}

    public static void main(String[] args) {
        _067_AddBinary obj = new _067_AddBinary();
        System.out.println(obj.addBinary("1010","1011"));
        System.out.println(obj.addBinary("11","1"));
        System.out.println(obj.addBinary("1","101"));
    }
}
