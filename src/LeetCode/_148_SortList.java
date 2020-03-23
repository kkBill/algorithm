package LeetCode;

public class _148_SortList {
    /**
     * 方法1：常规的归并思想
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(logn) 递归栈
     */
    /*
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        // 1.快慢指针确定中间节点, 当退出循环时, slow指向后半部分的第一个节点
        ListNode slow = head, fast = head, prev = null;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // 截断前半部分

        // 2.递归
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(slow);

        // 3.合并两个有序链表
        return merge(h1, h2);
    }

    */

    // 合并两个有序链表，返回合并后链表的首节点
    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                prev.next = h1;
                h1 = h1.next;
            } else {
                prev.next = h2;
                h2 = h2.next;
            }
            prev = prev.next;
        }
        if (h1 != null) prev.next = h1;
        else prev.next = h2;
        return dummy.next;
    }


    /**
     * 方法2：同样还是归并排序，不过采用了bottom-to-up的思想，空间复杂度优化至O(1)
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int length = getLength(head);
        // O(log n)
        for (int step = 1; step < length; step <<= 1) {
            ListNode prev = dummy;
            ListNode curr = prev.next;
            // O(n)
            while (curr != null) {
                ListNode left = curr;
                ListNode right = cut(left, step);
                curr = cut(right, step);
                prev.next = merge(left, right);
                // update prev for next iteration
                while (prev.next != null) {
                    prev = prev.next;
                }
            }
        }
        return dummy.next;
    }

//    Debug
//    public void printList(ListNode head) {
//        ListNode curr = head;
//        while (curr != null) {
//            System.out.print(curr.val + " ");
//            curr = curr.next;
//        }
//        System.out.println();
//    }


    // 分割链表: 截断从head节点开始(including head)的第n个节点，并返回截断之后半部分的新链表头
    // 原链表：2(head)->3->1->4->5->9->null
    // 调用 cut(head, 2) 后，链表变为：
    // part 1: 2->3->null
    // part 2: 1->4->5->9->null cut(head, 2) 返回节点1
    public ListNode cut(ListNode head, int n) {
        if(head == null) return null;

        ListNode curr = head;
        for (int i = 1; i < n && curr.next != null; i++) {
            curr = curr.next;
        }
        ListNode right = curr.next;
        curr.next = null; // 截断前n个节点
        return right;
    }

    // 获取链表长度
    public int getLength(ListNode head) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    public static void main(String[] args) {
        _148_SortList obj = new _148_SortList();
        ListNode head = ListNode.makeList(new int[]{4, 3, 2, 6, 5, 7, 1});
        head = obj.sortList(head);
        ListNode.showList(head);
    }
}
