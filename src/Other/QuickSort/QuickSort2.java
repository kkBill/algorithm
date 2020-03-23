package Other.QuickSort;

import LeetCode.ListNode;

public class QuickSort2 {
    // 基于链表的快速排序
    // 由于链表无法随机访问，主元的选取不再使用“3数取中”法，而是简单的取链表的第一个节点为pivot element
    public ListNode quickSort(ListNode head) {
        return qSort(head, null);
    }

    private ListNode qSort(ListNode head, ListNode end) {
        if (head == end || head.next == end) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pivot = head, prev = dummy;
        while (prev.next != end) {
            if (prev.next.val < pivot.val) {
                ListNode temp = prev.next;
                prev.next = prev.next.next;
                temp.next = dummy.next;
                dummy.next = temp;
            } else {
                prev = prev.next;
            }
        }

        //ListNode.showList(dummy.next);

        // 递归处理
        dummy.next = qSort(dummy.next, pivot);
        pivot.next = qSort(pivot.next, end);

        return dummy.next;
    }

    public static void main(String[] args) {
        QuickSort2 obj = new QuickSort2();
        int[] nums = new int[]{2,2,2,2};
        ListNode head = ListNode.makeList(nums);
        ListNode.showList(head);
        head = obj.quickSort(head);
        ListNode.showList(head);
    }
}
