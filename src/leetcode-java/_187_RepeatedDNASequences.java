package LeetCode;

import java.util.*;

public class _187_RepeatedDNASequences {
    // 暴力法，O(n^2)
    /*
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i <= n - 10; i++) {
            String sub = s.substring(i, i + 10);
            for (int j = i + 1; j <= n - 10; j++) {
                if (sub.equals(s.substring(j, j + 10))) {
                    result.add(sub);
                    break;
                }
            }
        }
        return result;
    }
    */

    // 借助哈希，两次遍历，O(n)
    /*
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
        }
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1){
                result.add(entry.getKey());
            }
        }
        return result;
    }
    */

    // 哈希的另一种做法，只需要一次遍历
    /*
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if(set.contains(sub)){
                result.add(sub);
            }else {
                set.add(sub);
            }
        }
        return new ArrayList<>(result);
    }
    */

    // 采用位运算做法~，时间复杂度也是O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() < 10) return new ArrayList<>();

        Set<String> result = new HashSet<>();
        Set<Integer> set = new HashSet<>();

        char[] chars = s.toCharArray();
        // 首先处理第一个长为10个字符的子串
        int key = 0;
        for(int i=0; i<10; i++){
            key <<= 2;
            key |= charToInt(chars[i]);
        }
        set.add(key);

        for(int i=10; i<s.length();i++){
            // 更新key：即原来的key左移2位，并加上当前这个字符
            key <<= 2;
            key |= charToInt(chars[i]);
            key &= 0xfffff; // 由于Java的int由32位，而我们只需要低20位
            if(set.contains(key)){
                result.add(s.substring(i-9,i+1));
            }else {
                set.add(key);
            }
        }
        return new ArrayList<>(result);
    }

    private int charToInt(char c){
        switch (c){
            case 'A': return 0;
            case 'G': return 1;
            case 'C': return 2;
            case 'T': return 3;
        }
        return -1;  // never happened
    }


    public static void main(String[] args) {
        _187_RepeatedDNASequences obj = new _187_RepeatedDNASequences();
        System.out.println(obj.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(obj.findRepeatedDnaSequences("AAAAAAAAAAAA"));
    }
}
