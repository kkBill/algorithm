package LeetCode;

public class _389_FindtheDifference {
    // 方法1：借助哈希，两次遍历
    /*
    public char findTheDifference(String s, String t) {
        int[] map = new int[26];
        for (char c : s.toCharArray())
            map[c - 'a']++;
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) return c;
        }
        return 'a';//never happens
    }
    */

    /*
    // 方法2：太秀了（当然存在溢出的风险，但是这个思路值得学习）
    public char findTheDifference(String s, String t) {
        int sum = 0;
        for (char c : t.toCharArray())
            sum += c - 'a';
        for (char c : s.toCharArray())
            sum -= c - 'a';
        return (char) (sum + 'a');
    }
    */

    /*
    // 方法3：位操作，其实思路和上面的一样
    public char findTheDifference(String s, String t) {
        int diff = 0;
        for (char c : t.toCharArray())
            diff ^= c - 'a';
        for (char c : s.toCharArray())
            diff ^= c - 'a';
        return (char)(diff + 'a');
    }*/

    public char findTheDifference(String s, String t) {
        char diff = 0;
        for (char c : t.toCharArray())
            diff ^= c;
        for (char c : s.toCharArray())
            diff ^= c;
        return diff;
    }

    public static void main(String[] args) {
        _389_FindtheDifference obj = new _389_FindtheDifference();
        System.out.println(obj.findTheDifference("abcd", "abcde"));
    }
}
