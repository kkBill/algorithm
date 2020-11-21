package LeetCode;

public class _147_InsertionSortList {
    /*
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preP1 = head, p1 = head.next; // 外侧遍历
        while (p1 != null) {
            ListNode preP2 = dummy, p2 = dummy.next; // 内层遍历
            while (p2 != p1) {
                if(p1.val < p2.val) break;
                preP2 = p2;
                p2 = p2.next;
            }
            if(p2 == p1){
                preP1 = p1;
                p1 = p1.next;
            }else {
                preP1.next = p1.next;
                p1.next = p2;
                preP2.next = p1;
                p1 = preP1.next;
            }
        }
        return dummy.next;
    }
     */


    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preP1 = head, p1 = head.next; // 外侧遍历
        while (p1 != null) {
            if (p1.val >= preP1.val) {
                preP1 = p1;
                p1 = p1.next;
                continue;
            }

            ListNode preP2 = dummy, p2 = dummy.next; // 内层遍历
            while (p2 != p1) {
                if (p1.val < p2.val) break;
                preP2 = p2;
                p2 = p2.next;
            }

            preP1.next = p1.next;
            p1.next = p2;
            preP2.next = p1;
            p1 = preP1.next;

        }
        return dummy.next;
    }

    public static void main(String[] args) {
        _147_InsertionSortList obj = new _147_InsertionSortList();
        ListNode head = ListNode.makeList(new int[]{1, 1});
        head = obj.insertionSortList(head);
        ListNode.showList(head);
    }
}
