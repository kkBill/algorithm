package LeetCode;

import java.util.Stack;

public class _232_ImplementQueueusingStacks {
    /** Initialize your data structure here. */
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    private int front;
    public _232_ImplementQueueusingStacks() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /*
    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        if(s2.isEmpty()){
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if(!s2.isEmpty()){
            return s2.peek();
        }else if(!s1.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
            return s2.peek();
        }else {
            throw new RuntimeException("ERROR: queue is empty");
        }
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }*/


    // 下面参考官方实现
    // time:O(1); space:O(n)
    public void push(int x) {
        if(s1.isEmpty()) {
            front = x;
        }
        s1.push(x);
    }

    // 时间复杂度： 摊还复杂度 O(1)，最坏情况下的时间复杂度 O(n)
    // space：O(1)
    public int pop() {
        if(s2.isEmpty()){
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if(!s2.empty()) {
            return s2.peek();
        }
        return front;
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        _232_ImplementQueueusingStacks queue = new _232_ImplementQueueusingStacks();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // returns 1
        System.out.println(queue.pop());   // returns 1
        System.out.println(queue.pop());   // returns 2
//        System.out.println(queue.peek());  // 题目中限定了不会对空的队列进行peek/pop操作。
        System.out.println(queue.empty()); // returns false
    }
}
