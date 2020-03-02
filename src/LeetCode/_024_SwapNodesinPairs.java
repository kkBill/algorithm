package LeetCode;

public class _024_SwapNodesinPairs {
    /**
     * 每两个节点进行交换（不允许通过改变节点的值来达到相同的效果）
     *
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        pre.next = head;
        while (pre.next != null && pre.next.next!= null) {
            ListNode t = pre.next.next;
            pre.next.next = t.next;
            t.next = pre.next;
            pre.next = t;
            pre = t.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        _024_SwapNodesinPairs obj = new _024_SwapNodesinPairs();
        ListNode head = ListNode.makeList(new int[]{1,2,3,4});
        head = obj.swapPairs(head);
        ListNode.showList(head);
    }
}
