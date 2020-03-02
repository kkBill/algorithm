package LeetCode;

import java.util.*;

public class _433_MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if(!set.contains(end)) return -1;

        char[] choices = new char[] {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int k = 0; k < size; k++) { //attention! "k < queue.size();" is wrong, cause queue.size() grow dynamically
                String temp = queue.poll();
                // 寻找temp的邻边
                for (int i = 0; i < temp.length(); i++) { //O(8)
                    for (int j = 0; j < choices.length; j++) { //O(4)
                        char[] word = temp.toCharArray();
                        word[i] = choices[j];
                        String newWord = String.valueOf(word);
                        if(newWord.equals(end)) {
                            return level;
                        }
                        if (set.contains(newWord)) {
                            queue.add(newWord);
                            set.remove(newWord); //相当于标记已经访问过
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        _433_MinimumGeneticMutation obj = new _433_MinimumGeneticMutation();
        String start = "AAAACCCC";
        String end = "CCCCCCCC";
        String[] bank = new String[] {"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};
        System.out.println(obj.minMutation(start, end, bank));
    }
}
