package LeetCode;

public class _061_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        int n = 0; // 链表长度
        ListNode curr = head, tail = null;
        while (curr != null) {
            if(curr.next == null) tail = curr; // 记录尾节点
            curr = curr.next;
            n++;
        }
        k %= n;
        if(k == 0) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode newTail = dummy;
        int i = 0;
        while (i < n-k) {
            newTail = newTail.next;
            i++;
        }
        // 这里的顺序不要搞错
        tail.next = dummy.next;
        dummy.next = newTail.next;
        newTail.next = null;

        return dummy.next;
    }

    public static void main(String[] args) {
        _061_RotateList obj = new _061_RotateList();
        ListNode head = ListNode.makeList(new int[]{1, 2});
        head = obj.rotateRight(head, 1);
        ListNode.showList(head);
    }
}
