package LeetCode;

public class _024_SwapNodesinPairs {
    /**
     * 每两个节点进行交换（不允许通过改变节点的值来达到相同的效果）
     */
    public ListNode swapPairs(ListNode head) {
        // 边界情况：链表为空，或只有一个节点（没必要）
        // if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        // 确保至少有两个节点不为空，否则不需要发生交换
        while (curr != null && curr.next != null) {
            // swap node
            ListNode post = curr.next;
            prev.next = post;
            curr.next = post.next;
            post.next = curr;
            // update
            prev = curr;
            curr = curr.next;
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
