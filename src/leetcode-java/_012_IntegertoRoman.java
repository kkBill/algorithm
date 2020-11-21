package LeetCode;

public class _012_IntegertoRoman {
    /**
     * 保证输入的值范围在[1,3999]
     */
    // 贪心
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int k = 0;
        while (k < values.length) {
            while (num >= values[k]){
                sb.append(roman[k]);
                num -= values[k];
            }
            k++;
        }
        return sb.toString();
    }

    /*
    public String intToRoman(int num) {
        String[][] table = new String[][]{
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 1~9
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 10~90
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 100~900
                {"", "M", "MM", "MMM"} };                                     // 1000~3000

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            sb.insert(0, table[i][num % 10]);
            i++;
            num /= 10;
        }
        return sb.toString();
    }
    */

    public static void main(String[] args) {
        _012_IntegertoRoman obj = new _012_IntegertoRoman();
        System.out.println(obj.intToRoman(58));
        System.out.println(obj.intToRoman(1994));


    }
}
