package JianZhiOffer;

import java.util.Stack;

public class _5queueImplementWithTwoStack {
    Stack<Integer> stack1 = new Stack<Integer>();// 存放
    Stack<Integer> stack2 = new Stack<Integer>();// 取值

    // 向队列中存放元素时，存到stack1中
    public void push(int node) {
        stack1.push(node);
    }
    // 从队列中取元素时，先检查stack2中是否含有元素，有则取之；无则从stack1中pop过来，再取之；
    // 如果stack1中也没有元素，则表示队列为空
    public int pop() {
        if(stack2.isEmpty() && stack1.isEmpty()){
            throw new RuntimeException("Queue is empty!"); // 这是参考来的，一开始我并不知道判题系统怎么处理这种情况
        }
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}