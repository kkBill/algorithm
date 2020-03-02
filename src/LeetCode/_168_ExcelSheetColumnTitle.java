package LeetCode;

public class _168_ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            int d = n % 26;
            if(d == 0){
                d = 26;
                n--;
            }
            sb.insert(0,(char)('A'+d-1));
            n /= 26;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        _168_ExcelSheetColumnTitle obj = new _168_ExcelSheetColumnTitle();
        System.out.println(obj.convertToTitle(1));
        System.out.println(obj.convertToTitle(26));
        System.out.println(obj.convertToTitle(52));
        System.out.println(obj.convertToTitle(28));
        System.out.println(obj.convertToTitle(5362));


    }
}
