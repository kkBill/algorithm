package LeetCode;

public class _014_LongestCommonPrefix {
    // 那么时间复杂度为O(n)，注意这里的n是字符串数组所有字符的长度之和
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        String base = strs[0];
        // 比较所有单词的第i个字符是否相等
        // 这里需要注意的是，可能有些单词的长度小于base的长度，
        // 因此在加一个判断条件"i == word.length()"，不然会导致 word.charAt(i) 越界
        for(int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            for(String word : strs) { // O(n)
                if(i == word.length() || word.charAt(i) != c){
                    return base.substring(0,i);
                }
            }
        }
        return base;
    }

    public static void main(String[] args) {
        _014_LongestCommonPrefix obj = new _014_LongestCommonPrefix();
        System.out.println(obj.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(obj.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
