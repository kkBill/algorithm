package LeetCode;

public class _058_LengthofLastWord {
    /*
    // 手动处理
    public int lengthOfLastWord(String s) {
        char[] str = s.toCharArray();
        int i = s.length() - 1;
        while (i >= 0 && str[i] == ' '){
            i--;
        }
        if(i == -1) return 0;
        int len = 0;
        while (i >= 0 && str[i] != ' '){
            i--;
            len++;
        }
        return len;
    }
    */

    // 熟悉一下字符串处理的api
    public int lengthOfLastWord(String s) {
        s = s.trim(); //This method may be used to trim whitespace from the beginning and end of a string.
        String[] words = s.split(" ");
        int n = words.length;
        if(n == 0) return 0;
        return words[n-1].length();
    }

    public static void main(String[] args) {
        _058_LengthofLastWord obj = new _058_LengthofLastWord();
        System.out.println(obj.lengthOfLastWord("    "));
    }
}
