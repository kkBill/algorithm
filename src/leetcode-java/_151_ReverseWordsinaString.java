package LeetCode;

public class _151_ReverseWordsinaString {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = words.length - 1; i >= 0; i--) {
            if(!"".equals(words[i])){
                sb.append(words[i]).append(" ");
            }
        }
        if(sb.length() == 0) return "";
        return sb.toString().substring(0,sb.length()-1);
    }

    public static void main(String[] args) {
        _151_ReverseWordsinaString obj = new _151_ReverseWordsinaString();
        System.out.println(obj.reverseWords("    aa ff"));
    }
}
