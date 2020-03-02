package LeetCode;

public class _021_MergeTwoSortedLists {
    // 自己写的版本代码太冗杂了
    /*
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // p1、p2为工作指针，head、tail为新构建链表的头指针和尾指针
        ListNode p1 = l1, p2 = l2, head = null, tail = null;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                if (head == null) {
                    head = p1;
                    tail = head;
                } else {
                    tail.next = p1;
                    tail = tail.next;
                }
                p1 = p1.next;
            } else {
                if (head == null) {
                    head = p2;
                    tail = head;
                } else {
                    tail.next = p2;
                    tail = tail.next;
                }
                p2 = p2.next;
            }
        }

        if (p1 != null) {
            if(tail == null) head = p1;
            else tail.next = p1;
        }
        if (p2 != null) {
            if(tail == null) head = p2;
            else tail.next = p2;
        }

        return head;
    }

    */

    // 精妙的代码~
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead;
        while (l1 != null && l2 != null) {
            if(l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else{
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 != null ? l1 : l2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        _021_MergeTwoSortedLists obj = new _021_MergeTwoSortedLists();
        ListNode l1 = ListNode.makeList(new int[]{1, 2, 5, 6});
        ListNode l2 = ListNode.makeList(new int[]{2, 3, 4});
        ListNode head = obj.mergeTwoLists(l1, l2);
        ListNode.showList(head);
    }
}
