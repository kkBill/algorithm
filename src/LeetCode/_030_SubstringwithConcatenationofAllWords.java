package LeetCode;

import java.util.List;

public class _030_SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        return null;
    }

    public static void main(String[] args) {
        _030_SubstringwithConcatenationofAllWords
                obj = new _030_SubstringwithConcatenationofAllWords();
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo","bar"};
        System.out.println(obj.findSubstring(s,words));

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word","good","best","word"};
        System.out.println(obj.findSubstring(s,words));
    }
}
