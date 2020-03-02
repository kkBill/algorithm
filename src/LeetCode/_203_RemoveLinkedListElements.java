package LeetCode;

public class _203_RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            if(prev.next.val == val) {
                prev.next = prev.next.next;
            }else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        _203_RemoveLinkedListElements obj = new _203_RemoveLinkedListElements();
        ListNode head = ListNode.makeList(new int[]{1,1,2,3,4});
        head = obj.removeElements(head,1);
        ListNode.showList(head);
    }
}
