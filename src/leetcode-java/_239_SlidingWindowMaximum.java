package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class _239_SlidingWindowMaximum {
    /**
     * 暴力法O(nk)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];// 特判，根据测试用例才想到的

        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {// O(n)
            int max = nums[i];
            for (int j = i; j < i + k; j++) {// O(k)
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }

    /**
     * 借助堆，O(n*log(k))
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (k * n == 0) return new int[0];

        int[] res = new int[n - k + 1];
        int len = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        for (int i = 0; i < n; i++) { // O(n)
            if (maxHeap.size() < k - 1) {
                maxHeap.add(nums[i]);
            } else {
                maxHeap.add(nums[i]); // O(log(k))
                res[len++] = maxHeap.peek(); // O(1)
                maxHeap.remove(nums[i - k + 1]); // O(log(k))
            }
        }
        return res;
    }

    /**
     * 借助双端端队列，O(n)
     * 这里的复杂度分析要注意，因为每个元素只进出deque一次，因此为O(n)
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (k * n == 0) return new int[0];

        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.add(i);
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _239_SlidingWindowMaximum obj = new _239_SlidingWindowMaximum();
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = obj.maxSlidingWindow3(nums, 3);
        for (int val : res) {
            System.out.print(val + " ");
        }
        System.out.println();

        // 特例
        nums = new int[]{};
        res = obj.maxSlidingWindow3(nums, 0);
        for (int val : res) {
            System.out.print(val + " ");
        }
    }
}
