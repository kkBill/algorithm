package LeetCode;

public class _082_RemoveDuplicatesfromSortedList2 {
    /*
    // 思路1
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        while (curr != null && curr.next != null) {
            if(curr.val == curr.next.val) {
                int duplicate = curr.val;
                while (curr != null && curr.val == duplicate) {
                    curr = curr.next;
                }
                prev.next = curr;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }

     */

    // 思路2: 巧妙，参考来的
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null) {
            ListNode curr = prev.next;
            while (curr.next != null && curr.next.val == curr.val){
                curr = curr.next;
            }
            if(curr != prev.next) {
                prev.next = curr.next;
            }else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        _082_RemoveDuplicatesfromSortedList2 obj = new _082_RemoveDuplicatesfromSortedList2();
        ListNode head = ListNode.makeList(new int[]{1,2,3,3,4,4,5});
        head = obj.deleteDuplicates(head);
        ListNode.showList(head);
    }
}
