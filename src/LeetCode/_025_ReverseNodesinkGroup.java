package LeetCode;

public class _025_ReverseNodesinkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, curr = head, tail = head;
        int cnt = 1;
        while (curr != null) {
            if (cnt % k == 0) {
                tail = curr.next;
                //System.out.println(prev.val + " " + head.val + " " + curr.val + " " + tail.val);
                prev.next = reverse(head, tail);
                curr = tail;
                prev = head;
                head = tail;
            } else {
                curr = curr.next;
            }
            cnt++;
        }
        prev.next = tail;
        return dummy.next;
    }

    // 翻转 [left, right) 区间的链表
    private ListNode reverse(ListNode left, ListNode right) {
        ListNode prev = null, curr = left, next = left;
        while (curr != right) {
            next = curr.next;
            curr.next = prev;
            // 更新
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        _025_ReverseNodesinkGroup obj = new _025_ReverseNodesinkGroup();
        ListNode head = ListNode.makeList(new int[]{1});
        head = obj.reverseKGroup(head, 2);
        ListNode.showList(head);
    }
}
