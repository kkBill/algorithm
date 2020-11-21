package LeetCode;

import java.util.PriorityQueue;

public class _703_KthLargestElementinaStream {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int K ;
    public _703_KthLargestElementinaStream(int k, int[] nums) {
        K = k;
        for(int val : nums){
            add(val);
        }
    }

    public int add(int val) {
        if(minHeap.size() < K){
            minHeap.add(val);
        }else if(minHeap.peek() < val){
            minHeap.poll();
            minHeap.add(val);
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        // 特例: k=1, nums=[]
        //       k=2, nums[0]

        int k = 2;
        int[] nums = new int[]{0};
        _703_KthLargestElementinaStream
                kthLargest  = new _703_KthLargestElementinaStream(k,nums);
        System.out.println(kthLargest.add(-1)); // -1
        System.out.println(kthLargest.add(1)); // 0
        System.out.println(kthLargest.add(-2)); // 0
        System.out.println(kthLargest.add(-4)); // 0
        System.out.println(kthLargest.add(3)); // 1
    }
}
