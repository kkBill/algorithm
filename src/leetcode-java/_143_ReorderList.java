package LeetCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _143_ReorderList {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度: O(n)
     */
    /*
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //while循环结束后，p1指向链表的中间节点，此时将p2重置为头节点
        p2 = head;

        Queue<ListNode> queue = new LinkedList<>();
        Stack<ListNode> stack = new Stack<>();
        //将p1指向的节点加入队列中，将p2指向的节点加入栈中
        while (p1 != null) {
            ListNode t1 = p1, t2 = p2;
            p1 = p1.next;
            p2 = p2.next;
            t1.next = null;
            t2.next = null;
            stack.add(t1);
            queue.add(t2);
        }

        ListNode curr = queue.poll();
        head = curr;
        curr.next = stack.pop();
        curr = curr.next;
        while (!queue.isEmpty()) {
            curr.next = queue.poll();
            curr = curr.next;
            if(curr != stack.peek()) {
                curr.next = stack.pop();
                curr = curr.next;
            }
        }
    }
    */

    /**
     *
     */
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode p1 = head, p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        // 翻转后半部分
        ListNode prev = null, curr = p1;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        p1 = prev; // prev此时为翻转后的链表的节点
        p2 = head;

        while (p1.next != null){
            ListNode t = p1;
            p1 = p1.next;
            t.next = p2.next;
            p2.next = t;
            p2 = p2.next.next;
        }
    }

    public static void main(String[] args) {
        _143_ReorderList obj = new _143_ReorderList();
        ListNode head = ListNode.makeList(new int[]{1,2});
        obj.reorderList(head);
        ListNode.showList(head);
    }
}
