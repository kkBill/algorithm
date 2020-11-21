package LeetCode;

import java.util.Stack;

public class _155_MinStack {


    /** initialize your data structure here. */
    private Stack<Integer> data; // 存放数据
    private Stack<Integer> helper; // helper的栈顶元素始终为当前data栈中元素的最小值

    public _155_MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.add(x);
        // 新元素较小，或者新元素小于当前helper的栈顶元素时，插入
        if(helper.isEmpty() || x <= helper.peek()){
            helper.add(x);
        }
    }

    public void pop() {
        if (!data.isEmpty()) {
            int peek = data.peek();
            data.pop();
            if (peek == helper.peek()) { // 这里int与Integer可直接进行比较（自动装箱/拆箱机制）
                helper.pop();
            }
        } else {
            throw new RuntimeException("ERROR: stack is empty.");
        }
    }

    public int top() {
        if(!data.isEmpty()){
            return data.peek();
        }else {
            throw new RuntimeException("ERROR: stack is empty.");
        }
    }

    public int getMin() {
        if(!helper.isEmpty()) {
            return helper.peek();
        }else {
            throw new RuntimeException("ERROR: stack is empty.");
        }
    }

    public static void main(String[] args) {
        _155_MinStack minStack = new _155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //Returns -3.
        minStack.pop();
        System.out.println(minStack.top());      //Returns 0.
        System.out.println(minStack.getMin());   //Returns -2.
    }
}
