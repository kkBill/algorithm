package LeetCode;

public class _038_CountandSay {
    public String countAndSay(int n) {
        String prevLine = "1";
        int k = 1;
        while (k < n){
            StringBuilder currLine = new StringBuilder();
            char[] s = prevLine.toCharArray();
            int i = 0, len = s.length;
            while(i < len) {
                char c = s[i];
                int count = 0;
                while (i < len && c == s[i]) {
                    i++;
                    count++;
                }
                currLine.append(count).append(c);
            }
            prevLine = currLine.toString();
            k++;
        }
        return prevLine;
    }

    public static void main(String[] args) {
        _038_CountandSay obj = new _038_CountandSay();
        System.out.println(obj.countAndSay(1));
        System.out.println(obj.countAndSay(2));
        System.out.println(obj.countAndSay(3));
        System.out.println(obj.countAndSay(5));
    }
}
