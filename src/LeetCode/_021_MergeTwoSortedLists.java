package LeetCode;

public class _021_MergeTwoSortedLists {
    // 这一题也是巧用 dummy 节点
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 == null) curr.next = l2;
        else curr.next = l1;

        return dummy.next;
    }

    public static void main(String[] args) {
        _021_MergeTwoSortedLists obj = new _021_MergeTwoSortedLists();
        ListNode l1 = ListNode.makeList(new int[]{1, 2, 5, 6});
        ListNode l2 = ListNode.makeList(new int[]{2, 3, 4});
        ListNode head = obj.mergeTwoLists(l1, l2);
        ListNode.showList(head);
    }
}
