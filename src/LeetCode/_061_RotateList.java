package LeetCode;

public class _061_RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        // 当链表为空，或只有一个节点
        if (head == null || head.next == null|| k <= 0) return head;
        int n = 0; // 链表长度
        ListNode curr = head, tail = null;
        while (curr != null) {
            if(curr.next == null) {
                tail = curr;
            }
            curr = curr.next;
            n++;
        }

        k = k % n;

        ListNode dummy = new ListNode(-1), pre = dummy;
        pre.next = head;
        ListNode newTail = pre;
        int i = 0;
        while (i < n-k) {
            newTail = newTail.next;
            i++;
        }
        tail.next = pre.next;
        pre.next = newTail.next;
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
