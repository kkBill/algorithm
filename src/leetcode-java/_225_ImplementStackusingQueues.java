package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class _225_ImplementStackusingQueues {

    /** Initialize your data structure here. */
    private Queue<Integer> data;
    private Queue<Integer> helper;
    public _225_ImplementStackusingQueues() {
        data = new LinkedList<>();
        helper = new LinkedList<>();
    }

    /** Push element x onto stack. */
    /*
    public void push(int x) {
        while (!data.isEmpty()){
            helper.add(data.poll());
        }
        data.add(x);
        while (!helper.isEmpty()){
            data.add(helper.poll());
        }
    }*/

    // 妙哉
    public void push(int x) {
        data.add(x);
        for(int i=0; i<data.size()-1;i++){
            data.add(data.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return data.poll();
    }

    /** Get the top element. */
    public int top() {
        return data.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        _225_ImplementStackusingQueues stack = new _225_ImplementStackusingQueues();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());   // returns 2
        System.out.println(stack.pop());   // returns 2
        System.out.println(stack.empty()); // returns false
    }
}
