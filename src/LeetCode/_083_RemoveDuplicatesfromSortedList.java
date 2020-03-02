package LeetCode;

public class _083_RemoveDuplicatesfromSortedList {
    /**
     * 不够简洁，最简洁的参考官方版本
     */
    /*
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1), pre;
        pre = dummy;
        pre.next = head;
        ListNode first = head, second = head.next;
        while (second != null) {
            while (second != null && first.val == second.val){
                first = first.next;
                second = second.next;
            }
            pre.next = first;
            pre = pre.next;
            first = first.next;
            if(second != null)
                second = second.next;
        }
        return dummy.next;
    }

    */

    /**
     * 参考官方版本
     */
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
