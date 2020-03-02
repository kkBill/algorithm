package LeetCode;

public class _019_RemoveNthNodeFromEndofList {
    /**
     * 双指针法
     * 初始化指针 p1 和 p2 指向第一个节点，然后，p2 率先移动 n 个节点，然后
     * p1、p2 同时移动，当 p2 到达 null 时，p1 指向的恰为倒数第 n 个节点
     * 由于链表的首节点可能被删除，增加一个 dummy 节点，方便统一处理
     *
     * 由于题目中说明了这里给出的 n 总是有效的，因此不必处理
     *
     * 时间复杂度：O(n) 因为只需要遍历一次节点
     * 空间复杂度：O(1) 没有额外节点
     */
    // 假定 n 总是有效的（1 ≤ n ≤ length）
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, curr = head, post = head;
        while (n > 0){
            post = post.next;
            n--;
        }
        // 当跳出循环时，curr 指向倒数第 n 个节点
        while (post != null) {
            prev = prev.next;
            curr = curr.next;
            post = post.next;
        }
        // remove operation
        prev.next = curr.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        _019_RemoveNthNodeFromEndofList obj = new _019_RemoveNthNodeFromEndofList();
        ListNode head = ListNode.makeList(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode.showList(head);
        System.out.println();
        head = obj.removeNthFromEnd(head, 7);
        ListNode.showList(head);
    }
}
