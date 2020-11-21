package LeetCode;

public class _092_ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = m; i < n; i++) {
            ListNode t = curr.next;
            curr.next = t.next;
            t.next = prev.next;
            prev.next = t;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        _092_ReverseLinkedList2 obj = new _092_ReverseLinkedList2();
        ListNode head = ListNode.makeList(new int[]{1, 2, 3, 4, 5});
        head = obj.reverseBetween(head, 2, 5);
        ListNode.showList(head);
    }
}
