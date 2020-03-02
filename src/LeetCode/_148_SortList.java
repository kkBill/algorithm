package LeetCode;

public class _148_SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 寻找中间节点(快慢指针)
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // 分割前一部分链表

        // 递归处理左右两部分
        ListNode p1 = sortList(head);
        ListNode p2 = sortList(slow);
        // 归并
        return mergeTwoSortedList(p1, p2);
    }

    private ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            ListNode t;
            if (head1.val <= head2.val) {
                t = head1;
                head1 = head1.next;
            } else {
                t = head2;
                head2 = head2.next;
            }
            t.next = null;
            tail.next = t;
            tail = tail.next;
        }
        if (head1 == null) tail.next = head2;
        if (head2 == null) tail.next = head1;
        return dummy.next;
    }

    public static void main(String[] args) {
        _148_SortList obj = new _148_SortList();
        ListNode head = ListNode.makeList(new int[]{1, 3, 2, 8, 4, 2, 5});
        head = obj.sortList(head);
        ListNode.showList(head);
    }
}
