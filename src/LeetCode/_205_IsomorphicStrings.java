package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _205_IsomorphicStrings {
    // 题目已经保证s和t的长度总是一样的
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> smap = new HashMap<>(); // s->t的映射
        Map<Character,Character> tmap = new HashMap<>(); // t->s的映射
        int n = s.length();
        for(int i = 0; i < n; i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if(!smap.containsKey(cs) && !tmap.containsKey(ct)){
                smap.put(cs, ct);
                tmap.put(ct, cs);
            }else if(smap.containsKey(cs) && tmap.containsKey(ct)){
                if(!smap.get(cs).equals(ct) || !tmap.get(ct).equals(cs)) return false;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _205_IsomorphicStrings obj = new _205_IsomorphicStrings();
        System.out.println(obj.isIsomorphic("egg","add"));
        System.out.println(obj.isIsomorphic("bar","foo"));
        System.out.println(obj.isIsomorphic("foo","bar"));
        System.out.println(obj.isIsomorphic("paper", "title"));
    }
}
