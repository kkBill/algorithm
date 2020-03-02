package LeetCode;

import java.util.Stack;

public class _445_AddTwoNumbers2 {
    // 方法1：先翻转链表，然后就和第2题一样了，返回结果前再把新的链表翻转一遍即可。

    /*
    // 方法2：借助栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while(l1 != null) {
            s1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.add(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = null;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int x1 = s1.isEmpty() ? 0 : s1.pop();
            int x2 = s2.isEmpty() ? 0 : s2.pop();
            int sum = x1 + x2 + carry;
            carry = sum / 10;
            sum %= 10;
            ListNode node = new ListNode(sum);
            if(head == null) {
                head = node;
            }else { // 头插法
                node.next = head;
                head = node;
            }
        }
        if(carry != 0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }
        return head;
    }
    */

    // 方法3：递归求解（不太熟悉）
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = len(l1);
        int n2 = len(l2);
        ListNode head = (n1 > n2) ? helper(l1, l2, n1 - n2) : helper(l2, l1, n2 - n1);
        if (head != null && head.val > 9) {
            head.val %= 10;
            ListNode pre = new ListNode(1);
            pre.next = head;
            head = pre;
        }
        return head;
    }

    // l1 的长度总是比 l2 的长度长，diff 为两者的长度差值
    private ListNode helper(ListNode l1, ListNode l2, int diff) {
        if (l1 == null) return null;
        ListNode curr = (diff == 0) ? new ListNode(l1.val + l2.val) : new ListNode(l1.val);
        ListNode post = (diff == 0) ? helper(l1.next, l2.next, 0) : helper(l1.next, l2, diff - 1);
        // 产生进位
        if (post != null && post.val > 9) {
            post.val %= 10;
            curr.val++;
        }
        curr.next = post;
        return curr;
    }

    private int len(ListNode head) {
        if (head == null) return 0;
        return 1 + len(head.next);
    }

    public static void main(String[] args) {
        _445_AddTwoNumbers2 obj = new _445_AddTwoNumbers2();

    }
}
