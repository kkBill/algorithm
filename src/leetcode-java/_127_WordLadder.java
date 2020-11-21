package LeetCode;


import javafx.util.Pair;

import java.util.*;

public class _127_WordLadder {
    /*
    // 时间复杂度：O(m*n) m是单词的长度，n是wordList中单词的个数
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean existEndWord = false;
        // 预处理，d*g -> dog, dig
        Map<String, List<String>> dict = new HashMap<>();
        for (String word : wordList) {
            if (word.equals(endWord)) {
                existEndWord = true;
            }
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = dict.getOrDefault(key, new ArrayList<>());
                list.add(word);
                dict.put(key, list);
            }
        }
        // 如果wordList中不存在endWord，直接返回0，结束程序
        if (!existEndWord) return 0;

        // BFS
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>(); // 标记某个节点是否已经被访问过
        queue.add(new Pair<>(beginWord, 1));
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.poll();
            String word = node.getKey();
            int depth = node.getValue();

            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (dict.containsKey(key)) {
                    List<String> childList = dict.get(key);
                    for (String child : childList) {
                        if (child.equals(endWord)) {
                            return depth + 1;
                        }
                        if (!visited.containsKey(child)) {
                            queue.add(new Pair<>(child, depth + 1));
                            visited.put(child, true);
                        }
                    }
                }
            }
        }
        return 0;
    }
    */

    // 双向BFS
    private Map<String, List<String>> dict = new HashMap<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean existEndWord = false;
        // 预处理，d*g -> dog, dig
        for (String word : wordList) {
            if (word.equals(endWord)) {
                existEndWord = true;
            }
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = dict.getOrDefault(key, new ArrayList<>());
                list.add(word);
                dict.put(key, list);
            }
        }
        // 如果wordList中不存在endWord，直接返回0，结束程序
        if (!existEndWord) return 0;

        // 双向广度优先搜索, 1 记录从上至下；2 记录从下至上
        Queue<Pair<String, Integer>> queue1 = new LinkedList<>();
        Map<String, Integer> visited1 = new HashMap<>();
        queue1.add(new Pair<>(beginWord, 1));
        visited1.put(beginWord, 1);

        Queue<Pair<String, Integer>> queue2 = new LinkedList<>();
        Map<String, Integer> visited2 = new HashMap<>();
        queue2.add(new Pair<>(endWord, 1));
        visited2.put(endWord, 1);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int ans1 = visitNode(queue1,visited1,visited2);
            if(ans1 != -1) return ans1;

            int ans2 = visitNode(queue2,visited2,visited1);
            if(ans2 != -1) return ans2;
        }
        return 0;
    }

    private int visitNode(Queue<Pair<String, Integer>> queue,
                          Map<String, Integer> visited1,
                          Map<String, Integer> visited2) {
        Pair<String, Integer> node = queue.poll();
        String word = node.getKey();
        int depth = node.getValue();

        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + "*" + word.substring(i + 1);
            if (dict.containsKey(key)) {
                List<String> childList = dict.get(key);
                for(String child : childList) {
                    // 如果该节点已经被另一个方向访问过了，可以直接返回结果
                    if(visited2.containsKey(child)) {
                        return depth + visited2.get(child);
                    }

                    if(!visited1.containsKey(child)) {
                        queue.add(new Pair<>(child, depth + 1));
                        visited1.put(child, depth + 1);
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        _127_WordLadder obj = new _127_WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(obj.ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }
}
