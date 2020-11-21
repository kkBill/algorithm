package LeetCode;

public class _328_OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode prev = head, curr = head.next, evenHead = head.next;
        while (curr != null && curr.next != null) {
            prev.next = curr.next;
            prev = prev.next;

            curr.next = (prev != null ? prev.next : null);
            curr = curr.next;
        }
        prev.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        _328_OddEvenLinkedList obj = new _328_OddEvenLinkedList();
        ListNode head = ListNode.makeList(new int[]{2, 1, 3});
        head = obj.oddEvenList(head);
        ListNode.showList(head);
    }
}
