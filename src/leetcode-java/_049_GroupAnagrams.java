package LeetCode;

import java.util.*;

public class _049_GroupAnagrams {
/**
 * 方法1：
 * 这种做法的效率很好，但是有溢出的风险。这种思想是非常的值得借鉴的。
 * 时间复杂度：O(n*k) n是单词数组的长度，k是单词的长度
 * 空间复杂度：O(n*k) 哈希表存储单词
 */
/*
public List<List<String>> groupAnagrams(String[] strs) {
    // 每个字符对应一个质数，比如map[0] = 2,代表字符a对应质数2
    int[] map = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};

    List<List<String>> res = new ArrayList<>();
    Map<Long, List<String>> dict = new HashMap<>();
    for(String word : strs) {
        long product = 1;
        for(char c : word.toCharArray()) {
            product *= map[c-'a'];
        }
        if(!dict.containsKey(product)){
            List<String> s = new ArrayList<>();
            s.add(word);
            dict.put(product,s);
        }else {
            dict.get(product).add(word);
        }
    }

    for(Map.Entry<Long, List<String>> entry : dict.entrySet()) {
        res.add(entry.getValue());
    }
    return res;
}

*/

//    private void helper() {
//        for(int i = 2, count = 0; count < 26; i++) {
//            if(prime(i)){
//                //map[count] = i;
//                System.out.print(i + " ");
//                count++;
//            }
//        }
//    }
//
//    private boolean prime(int n) {
//        if(n <= 1) return false;
//        for(int i = 2; i * i <= n; i++){
//            if(n % i == 0) return false;
//        }
//        return true;
//    }

/**
 * 方法2：排序法
 * 时间复杂度：O(n* k*log(k)) k 是最长单词的长度
 * 空间复杂度：O(n*k)
 */
/*
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for(String word : strs) { //O(n)
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray); //O(k*log(k))
        String key = String.valueOf(charArray);

        if(map.containsKey(key)) {
            map.get(key).add(word);
        }else {
            List<String> s = new ArrayList<>();
            s.add(word);
            map.put(key,s);
        }
    }

    return new ArrayList<>(map.values()); // 更快速的写法，不用自己遍历map构建res
}
*/

// O(n*k)
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for(String word : strs) { //O(n)
        char[] charArray = word.toCharArray();
        // 统计单词 word 中出现的字符个数
        int[] count = new int[26];
        for(char c : charArray) {
            count[c-'a']++;
        }
        // 将单词转化成 2#3#...的形式
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < count.length; i++) { //O(k)
            key.append(count[i]).append('#');
        }

        if(map.containsKey(key.toString())) {
            map.get(key.toString()).add(word);
        }else {
            List<String> s = new ArrayList<>();
            s.add(word);
            map.put(key.toString(),s);
        }
    }

    return new ArrayList<>(map.values());
}

    public static void main(String[] args) {
        _049_GroupAnagrams obj = new _049_GroupAnagrams();
        String[] words = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(obj.groupAnagrams(words));
//        obj.helper();
    }
}
