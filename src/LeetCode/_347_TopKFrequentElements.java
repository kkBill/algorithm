package LeetCode;

import java.util.*;

public class _347_TopKFrequentElements {
    /**
     * 时间复杂度：O(n*log(k))
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>>
                minHeap = new PriorityQueue<>((x, y) -> (x.getValue() - y.getValue()));

        for (int num : nums) { // O(n)
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) { // O(n)
            if (minHeap.size() < k) {
                minHeap.add(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.add(entry); // O(log(k))
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!minHeap.isEmpty()) {
            Map.Entry<Integer, Integer> entry = minHeap.poll();
            result.add(0, entry.getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        _347_TopKFrequentElements obj = new _347_TopKFrequentElements();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(obj.topKFrequent(nums, k));
    }
}
