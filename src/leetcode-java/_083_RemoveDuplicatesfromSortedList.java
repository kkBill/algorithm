package LeetCode;

public class _083_RemoveDuplicatesfromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if(curr.val == curr.next.val) {
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
            }
        }
        return head;
    }
    public static void main(String[] args) {
        _083_RemoveDuplicatesfromSortedList obj = new _083_RemoveDuplicatesfromSortedList();
        ListNode head = ListNode.makeList(new int[]{1,1,1,1,1,1});
        head = obj.deleteDuplicates(head);
        ListNode.showList(head);
    }
}
