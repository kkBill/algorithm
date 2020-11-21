package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _862_ShortestSubarraywithSumatLeastK {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)  这里使用前缀和进行数据预处理
     */
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] B = new int[n + 1];
        // 计算前缀和
        for (int i = 0; i < n; i++) {
            B[i + 1] = A[i] + B[i];
        }

        int minLen = n + 1;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n + 1; i++) {
            while (!deque.isEmpty() && B[i] - B[deque.getFirst()] >= K) {
                minLen = Math.min(minLen, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && B[i] <= B[deque.getLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        return (minLen == n + 1) ? -1 : minLen;
    }

    public static void main(String[] args) {
        _862_ShortestSubarraywithSumatLeastK obj = new _862_ShortestSubarraywithSumatLeastK();
        int[] A = new int[]{3, -2, 5};
        int K = 4;
        System.out.println(obj.shortestSubarray(A, K));
    }
}
