package LeetCode;

import java.util.PriorityQueue;

public class _295_FindMedianfromDataStream {

    private PriorityQueue<Integer> minHeap; // 小顶堆
    private PriorityQueue<Integer> maxHeap; // 大顶堆

    public _295_FindMedianfromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((x, y) -> y - x);// 也可以写成 new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(maxHeap.size() < minHeap.size()){
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size())
            return (double)(maxHeap.peek() + minHeap.peek()) * 0.5;
        else
            return (double)maxHeap.peek();
    }

    public static void main(String[] args) {
        _295_FindMedianfromDataStream obj = new _295_FindMedianfromDataStream();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}
