package LeetCode;

public class _028_ImplementstrStr {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
    public static void main(String[] args) {
        _028_ImplementstrStr obj = new _028_ImplementstrStr();
        System.out.println(obj.strStr("hello",""));
    }
}
