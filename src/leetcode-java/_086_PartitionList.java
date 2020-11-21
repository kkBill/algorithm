package LeetCode;

public class _086_PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1), dummy2 = new ListNode(-1);
        ListNode curr1 = dummy1, curr2 = dummy2;
        while(head != null) {
            ListNode node = head;
            head = head.next;
            node.next = null;
            if(node.val < x) {
                curr1.next = node;
                curr1 = curr1.next;
            }else {
                curr2.next = node;
                curr2 = curr2.next;
            }
        }
        curr1.next = dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {
        _086_PartitionList obj = new _086_PartitionList();
        ListNode head = ListNode.makeList(new int[]{1, 2, 3, 3, 4, 2, 1});
        head = obj.partition(head, 3);
        ListNode.showList(head);
    }
}