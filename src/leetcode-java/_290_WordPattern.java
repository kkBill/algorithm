package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _290_WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if(pattern.length() != words.length) return false; //如果长度不相等，就肯定满足映射关系
        int n = pattern.length();

        Map<Character, String> pmap = new HashMap<>(); // pattern -> str 的映射
        Map<String, Character> smap = new HashMap<>(); // str -> pattern 的映射

        for(int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if(!pmap.containsKey(c) && !smap.containsKey(w)){
                pmap.put(c, w);
                smap.put(w, c);
            }else if(pmap.containsKey(c) && smap.containsKey(w)){
                if(!pmap.get(c).equals(w) || !smap.get(w).equals(c))
                    return false;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _290_WordPattern obj = new _290_WordPattern();
        System.out.println(obj.wordPattern("aaaa", "dog cat cat dog"));//false
        System.out.println(obj.wordPattern("abba", "dog cat cat dog"));//true
        System.out.println(obj.wordPattern("abba", "dog dog dog dog"));//false
    }
}
