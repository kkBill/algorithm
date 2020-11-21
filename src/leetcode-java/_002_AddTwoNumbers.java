package LeetCode;

import java.util.Stack;

public class _002_AddTwoNumbers {

    // 版本1（比较冗杂）
    /*
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0; // 进位
        int sum = 0, digit = 0;
        ListNode head = null, curr = null;
        while(l1 != null && l2 != null) {
            sum = carry + l1.val + l2.val;
            carry = sum / 10;
            digit = sum % 10;
            ListNode node = new ListNode(digit);
            if(head == null) {
                head = node;
                curr = head;
            }else {
                curr.next = node;
                curr = curr.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        // case 1
        while(l1 != null) {
            sum = carry + l1.val;
            carry = sum / 10;
            digit = sum % 10;
            curr.next = new ListNode(digit);
            curr = curr.next;

            l1 = l1.next;
        }

        while(l2 != null) {
            sum = carry + l2.val;
            carry = sum / 10;
            digit = sum % 10;
            curr.next = new ListNode(digit);
            curr = curr.next;

            l2 = l2.next;
        }

        // case 2
        if(carry == 1) {
            curr.next = new ListNode(carry);
        }

        return head;
    }

     */

    // 代码更加精简
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0; // 进位
        int sum = 0, digit = 0;
        ListNode head = null, curr = null;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            sum = carry + x + y;
            carry = sum / 10;
            digit = sum % 10;
            ListNode node = new ListNode(digit);
            if (head == null) {
                head = node;
                curr = head;
            } else {
                curr.next = node;
                curr = curr.next;
            }
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry == 1) {
            curr.next = new ListNode(carry);
        }

        return head;
    }

    // 扩展，数值是顺序存放的：比如(3→4→2)+(4→6→5)=8→0→7
    // 注意点1：使用栈预处理一下，接下来的处理和上一题一样
    // 注意点2：构建链表应该使用头插法！
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        // 预处理
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        int carry = 0, sum = 0, digit = 0;
        ListNode head = null;
        while (!s1.empty() || !s2.empty()) {
            int x = s1.empty() ? 0 : s1.pop().val;
            int y = s2.empty() ? 0 : s2.pop().val;
            sum = carry + x + y;
            carry = sum / 10;
            digit = sum % 10;
            ListNode node = new ListNode(digit);
            if(head == null) {
                head = node;
            }else{
                // 头插法，新插入的节点作为头节点
                node.next = head;
                head = node;
            }
        }
        if(carry == 1){
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    public static void main(String[] args) {
        _002_AddTwoNumbers obj = new _002_AddTwoNumbers();
        ListNode l1 = ListNode.makeList(new int[]{1});
        ListNode.showList(l1);
        System.out.println();
        ListNode l2 = ListNode.makeList(new int[]{7, 8, 9});
        ListNode.showList(l2);
        System.out.println();
        ListNode sum = obj.addTwoNumbers2(l1, l2);
        ListNode.showList(sum);
    }
}
